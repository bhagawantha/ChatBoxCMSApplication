/**
 * 
 * 
 * Description : This class used for displaying the homepage and the logged out page views.
 * 
 * @author Bhagawantha
 * @version 1.0
 * @see
 * @since Dec 05, 2016
 */
package com.chatbox.cms.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.chatbox.cms.dto.CommentsDto;
import com.chatbox.cms.dto.DiscussionDto;
import com.chatbox.cms.entity.TopicAttachmentEntity;
import com.chatbox.cms.entity.TopicCommentEntity;
import com.chatbox.cms.entity.TopicEntity;
import com.chatbox.cms.exception.ChatBoxBusinessException;
import com.chatbox.cms.exception.ChatBoxDataAccessException;

/**
 * @author Bhagawantha Parasuraman
 *
 */
@Repository
@EnableTransactionManagement
public class DiscussionDaoImpl implements DiscussionDao {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(DiscussionDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * @see com.chatbox.cms.dao.DiscussionDao#getAllTopics()
	 */
	@Override
	public List<DiscussionDto> getAllTopics() {
		List<DiscussionDto> discussionList = new ArrayList<DiscussionDto>();
		Session session = sessionFactory.openSession();
		try {
			session = sessionFactory.openSession();
			List<TopicEntity> allTopicsList = (List<TopicEntity>) session
					.createQuery("SELECT t from TopicEntity t").list();

			System.out.println("** Dao allTopics : " + allTopicsList.size());

			generatingDiscussionDto(discussionList, allTopicsList);

		} finally {
			closeSession(session);
		}

		return discussionList;
	}

	/**
	 * @see com.chatbox.cms.dao.DiscussionDao#getAllTopics()
	 */
	@Override
	public DiscussionDto getTopic(int topicId) {
		List<DiscussionDto> discussionList = new ArrayList<DiscussionDto>();
		Session session = sessionFactory.openSession();
		try {
			List<TopicEntity> allTopicsList = new ArrayList<TopicEntity>();
			session = sessionFactory.openSession();
			TopicEntity tpEntity = (TopicEntity) session.load(
					TopicEntity.class, topicId);
			allTopicsList.add(tpEntity);
			System.out.println("** Dao allTopics : " + allTopicsList.size());

			generatingDiscussionDto(discussionList, allTopicsList);

		} finally {
			closeSession(session);
		}

		return discussionList.get(0);
	}

	/**
	 * @param session
	 */
	private void closeSession(Session session) {
		if (null != session && session.isOpen()) {
			session.close();
		}
	}

	private void generatingDiscussionDto(List<DiscussionDto> discussionList,
			List<TopicEntity> allTopicsList) {
		for (TopicEntity topic : allTopicsList) {
			DiscussionDto dissDto = new DiscussionDto();
			dissDto.setDiscussionId(topic.getTopicId());
			dissDto.setTopic(topic.getTopicName());
			dissDto.setDescription(topic.getDescription());
			dissDto.setCreatedBy(topic.getCreatedBy());
			dissDto.setUpdatedBy(topic.getUpdatedBy());
			dissDto.setModifiedDate(topic.getUpdatedOn());
			dissDto.setCreatedDate(topic.getCreatedOn());
			List<CommentsDto> comments = new ArrayList<CommentsDto>();
			for (TopicCommentEntity comment1 : topic.getTopicCommentEntities()) {
				CommentsDto commDto = new CommentsDto();
				commDto.setCreatedBy(comment1.getCreatedBy());
				commDto.setCreatedDate(comment1.getCreatedOn());
				commDto.setDescription(comment1.getComments());
				commDto.setModifiedDate(comment1.getUpdatedOn());
				comments.add(commDto);
			}
			List<String> fileLocationList = new ArrayList<String>();
			List<String> fileTypeList = new ArrayList<String>();
			
			for (TopicAttachmentEntity attch1 : topic.getTopicAttachmentEntities()) {
				String fileLoc = attch1.getFileLocation();
				fileLocationList.add(fileLoc);
				fileTypeList.add(generateDocType(fileLoc));
			}
			dissDto.setFileLocation(fileLocationList);
			dissDto.setFileType(fileTypeList);
			dissDto.setComments(comments);
			dissDto.setCommentsCount(comments.size());
			discussionList.add(dissDto);
		}

	}

	/**
	 * @throws ChatBoxBusinessException
	 * @see com.chatbox.cms.dao.DiscussionDao#storeDiscussion(com.chatbox.cms.dto.DiscussionDto)
	 */
	@Transactional
	@Override
	public DiscussionDto storeDiscussion(DiscussionDto discussionDto)
			throws ChatBoxDataAccessException {
		int topicIdInt = discussionDto.getDiscussionId();
		if (topicIdInt == 0) {
			Session session = null;
			try {
				session = sessionFactory.openSession();
				session.getTransaction().begin();
				TopicEntity topic = storeTopic(discussionDto, session);
				session.flush();
				storeAttachment(discussionDto, topic, session);
				discussionDto.setDiscussionId(topicIdInt);
				session.getTransaction().commit();
			} catch (HibernateException excep) {
				LOGGER.error(":: ChatBoxDataAccessException :: exception",
						excep);
				throw new ChatBoxDataAccessException(excep.getMessage(), excep);
			} finally {
				session.flush();
				closeSession(session);
			}
		}

		return discussionDto;
	}

	/**
	 * 
	 * @throws ChatBoxBusinessException
	 * @see com.chatbox.cms.dao.DiscussionDao#removeDiscussion(int)
	 */
	@Override
	@Transactional
	public boolean removeDiscussion(int discussionId)
			throws ChatBoxDataAccessException {
		Session session = null;
		boolean flag = false;
		try {
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			TopicEntity tpEntity = (TopicEntity) session.load(
					TopicEntity.class, discussionId);
			if(null!=tpEntity.getTopicAttachmentEntities()&& tpEntity.getTopicAttachmentEntities().size()>0){
				for (TopicAttachmentEntity topicAt : tpEntity.getTopicAttachmentEntities()) {
					session.delete(topicAt);
					session.flush();
				}
				
			}
			if(null!=tpEntity.getTopicCommentEntities()&& tpEntity.getTopicCommentEntities().size()>0){
				for (TopicCommentEntity topicCom : tpEntity.getTopicCommentEntities()) {
					session.delete(topicCom);
					session.flush();
				}
				
			}
			
			session.delete(tpEntity);
			session.flush();
			tx.commit();
			flag = true;
		} catch (HibernateException excep) {
			LOGGER.error(":: ChatBoxDataAccessException :: exception", excep);
			throw new ChatBoxDataAccessException(excep.getMessage(), excep);
		} finally {
			session.flush();
			closeSession(session);
		}
		return flag;
	}

	/**
	 * @param discussionDto
	 */
	private TopicEntity storeTopic(DiscussionDto discussionDto, Session session) {
		TopicEntity topic = new TopicEntity();
		Date dt1 = Calendar.getInstance().getTime();
		topic.setTopicName(discussionDto.getTopic());
		topic.setDescription(discussionDto.getDescription());
		if (discussionDto.getDiscussionId() <= 0) {
			topic.setCreatedBy(discussionDto.getCreatedBy());
			topic.setCreatedOn(dt1);
			session.save(topic);
		} else {
			topic.setUpdatedBy(discussionDto.getCreatedBy());
			topic.setUpdatedOn(dt1);
			session.saveOrUpdate(topic);
		}
		return topic;

	}

	/**
	 * @param discussionDto
	 * @param topic
	 */
	private void storeAttachment(DiscussionDto discussionDto,
			TopicEntity topic, Session session) {
		if (null != discussionDto.getFileLocation()
				&& discussionDto.getFileLocation().size() > 0) {
			Set<TopicAttachmentEntity> topicAttachmentSet = new HashSet<TopicAttachmentEntity>();
			Date dt1 = Calendar.getInstance().getTime();
			for (String fileLocation : discussionDto.getFileLocation()) {
				TopicAttachmentEntity attachment = new TopicAttachmentEntity();
				attachment.setFileLocation(fileLocation);
				if (discussionDto.getDiscussionId() <= 0) {
					attachment.setCreatedBy(discussionDto.getCreatedBy());
					attachment.setCreatedOn(dt1);
					attachment.setTopicEntity(topic);
					session.save(attachment);
				} else {
					topic.setUpdatedBy(discussionDto.getCreatedBy());
					topic.setUpdatedOn(dt1);
					session.saveOrUpdate(attachment);
				}
				topicAttachmentSet.add(attachment);
			}
			topic.setTopicAttachmentEntities(topicAttachmentSet);

		}

	}

	private String generateDocType(String fileLocation) {
		String docType = null;
		if(fileLocation.contains(".jpeg")||fileLocation.contains(".jpg")||fileLocation.contains(".png")||fileLocation.contains(".bmp")){
			docType = "image";
		}else if(fileLocation.contains(".mp4")||fileLocation.contains(".avi")){
			//mp4,avi
			docType = "video";
		}else{
			docType = "doc";
		}
		return docType;
	}

	/**
	 * @param discussionDto
	 * @param topic
	 */
	private void storeTopicComment(DiscussionDto discussionDto,
			TopicEntity topic) {
		if (null != discussionDto.getComments()
				&& discussionDto.getComments().size() > 0) {
			Set<TopicCommentEntity> topicCommentSet = new HashSet<TopicCommentEntity>();
			Date dt1 = Calendar.getInstance().getTime();
			for (CommentsDto commentsDto : discussionDto.getComments()) {
				TopicCommentEntity topicComment = new TopicCommentEntity();
				topicComment.setComments(commentsDto.getDescription());
				topicComment.setCreatedBy(discussionDto.getCreatedBy());
				topicComment.setUpdatedBy(discussionDto.getCreatedBy());
				topicComment.setCreatedOn(dt1);
				topicComment.setUpdatedOn(dt1);
				topicCommentSet.add(topicComment);
			}
			topic.setTopicCommentEntities(topicCommentSet);
		}

	}

	@Override
	public boolean storeComment(String comment, int discussionId, String userNam)
			throws ChatBoxDataAccessException {
		TopicEntity topic = null;
		boolean flag = false;
		Session session = sessionFactory.openSession();
		try {
			session = sessionFactory.openSession();
			Date dt = Calendar.getInstance().getTime();
			topic = (TopicEntity) session.load(TopicEntity.class, discussionId);
			TopicCommentEntity commentEntity = new TopicCommentEntity();
			commentEntity.setComments(comment);
			commentEntity.setCreatedOn(dt);
			commentEntity.setCreatedBy(userNam);
			commentEntity.setUpdatedBy(userNam);
			commentEntity.setUpdatedOn(dt);
			commentEntity.setTopicEntity(topic);
			session.save(commentEntity);
			flag = true;
		} catch (HibernateException excep) {
			LOGGER.error(":: ChatBoxDataAccessException :: exception", excep);
			throw new ChatBoxDataAccessException(excep.getMessage(), excep);
		} finally {
			session.flush();
			closeSession(session);
		}
		return flag;

	}
}
