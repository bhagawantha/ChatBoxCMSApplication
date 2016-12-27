/**
 * Description : This class used for setting the Hibernate session factory
 * 
 * @author Bhagawantha
 * @version 1.0
 * @see
 * @since Dec 08, 2016
 */

package com.chatbox.cms.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chatbox.cms.dao.DiscussionDao;
import com.chatbox.cms.dto.CommentsDto;
import com.chatbox.cms.dto.DiscussionDto;
import com.chatbox.cms.exception.ChatBoxBusinessException;
import com.chatbox.cms.exception.ChatBoxDataAccessException;

/**
 * @author Bhagawantha Parasuraman
 *
 */
@Service
public class DiscussionForumServiceImpl implements DiscussionForumService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(DiscussionForumServiceImpl.class);

	@Autowired
	DiscussionDao discussionDao;

	List<DiscussionDto> discussionDtoList;

	@Override
	@Transactional
	public List<DiscussionDto> viewBriefDiscussion() {
		List<DiscussionDto> discussionList = new ArrayList<DiscussionDto>();

		discussionList = discussionDao.getAllTopics();
		return discussionList;
	}

	/**
	 * 
	 * @throws ChatBoxBusinessException
	 * @see com.chatbox.cms.services.DiscussionForumService#storeDiscussion(com.chatbox.cms.dto.DiscussionDto)
	 */
	@Override
	public DiscussionDto storeDiscussion(DiscussionDto discussionDto)
			throws ChatBoxBusinessException {
		try {
			discussionDto = discussionDao.storeDiscussion(discussionDto);
		} catch (ChatBoxDataAccessException e) {
			discussionDto = null;
			LOGGER.error(":: storeDiscussion :: excep :: ", e);
			throw new ChatBoxBusinessException(e.getMessage(), e);
		}
		return discussionDto;
	}

	/**
	 * 
	 * @throws ChatBoxBusinessException
	 * @see com.chatbox.cms.services.DiscussionForumService#removeDiscussion(int)
	 */
	@Override
	public boolean removeDiscussion(int discussionId)
			throws ChatBoxBusinessException {
		boolean flag = false;
		try {
			flag = discussionDao.removeDiscussion(discussionId);
		} catch (ChatBoxDataAccessException e) {
			flag = true;
			LOGGER.error(":: removeDiscussion :: excep :: ", e);
			throw new ChatBoxBusinessException(e.getMessage(), e);
		}
		return flag;
	}

	@Override
	public DiscussionDto getDiscussionById(int discussionId) {
		DiscussionDto discussionDto = discussionDao.getTopic(discussionId);
		return discussionDto;

	}

	@Override
	public List<CommentsDto> storeComment(int discussionId, String comment,
			String userName) throws ChatBoxBusinessException {
		List<CommentsDto> commentsDtoList = new ArrayList<CommentsDto>();
		try {
			DiscussionDto discussDto = discussionDao.getTopic(discussionId);
			if (null != discussDto && null != discussDto.getComments()) {
				for (CommentsDto commentsDto1 : commentsDtoList) {
					commentsDtoList.add(commentsDto1);
				}
			}
			Date dt = Calendar.getInstance().getTime();
			CommentsDto commentsDto = new CommentsDto();
			commentsDto.setDescription(comment);
			commentsDto.setCreatedDate(dt);
			commentsDto.setCreatedBy(userName);
			commentsDto.setUpdatedBy(userName);
			commentsDto.setModifiedDate(dt);
			commentsDtoList.add(commentsDto);

			discussionDao.storeComment(comment, discussionId, userName);
		} catch (ChatBoxDataAccessException excep) {
			LOGGER.error(":: storeComment :: excep :: ", excep);
			throw new ChatBoxBusinessException(excep.getMessage(), excep);
		}
		return commentsDtoList;
	}

	/**
	 * @return the discussionDao
	 */
	public DiscussionDao getDiscussionDao() {
		return discussionDao;
	}

	/**
	 * @param discussionDao the discussionDao to set
	 */
	public void setDiscussionDao(DiscussionDao discussionDao) {
		this.discussionDao = discussionDao;
	}

}
