package com.chatbox.cms.dao;

import java.util.List;

import com.chatbox.cms.dto.DiscussionDto;
import com.chatbox.cms.exception.ChatBoxBusinessException;
import com.chatbox.cms.exception.ChatBoxDataAccessException;

public interface DiscussionDao {

	/**
	 * @return
	 */
	List<DiscussionDto> getAllTopics();

	/**
	 * @param discussionDto
	 * @return
	 * @throws ChatBoxBusinessException 
	 */
	DiscussionDto storeDiscussion(DiscussionDto discussionDto) throws ChatBoxDataAccessException;

	/**
	 * @param discussionId
	 * @return
	 * @throws ChatBoxBusinessException 
	 */
	boolean removeDiscussion(int discussionId) throws ChatBoxDataAccessException;

	/**
	 * @param comments1
	 * @param discussionId
	 * @return
	 * @throws ChatBoxDataAccessException 
	 */
	boolean storeComment(String comments1, int discussionId, String userName) throws ChatBoxDataAccessException;

	DiscussionDto getTopic(int topicId);
}
