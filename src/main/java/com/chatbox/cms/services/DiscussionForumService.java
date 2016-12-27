/**
 * Description : This class used for setting the Hibernate session factory
 * 
 * @author Bhagawantha
 * @version 1.0
 * @see
 * @since Dec 01, 2016
 */
 
package com.chatbox.cms.services;

import java.util.List;

import com.chatbox.cms.dto.CommentsDto;
import com.chatbox.cms.dto.DiscussionDto;
import com.chatbox.cms.exception.ChatBoxBusinessException;

/**
 * @author Bhagawantha Parasuraman
 *
 */
public interface DiscussionForumService {

	public List<DiscussionDto> viewBriefDiscussion();

	public DiscussionDto storeDiscussion(DiscussionDto discussionDto) throws ChatBoxBusinessException;

	public boolean removeDiscussion(int parseInt) throws ChatBoxBusinessException;

	public DiscussionDto getDiscussionById(int parseInt);

	public List<CommentsDto> storeComment(int parseInt, String comment, String usrName) throws ChatBoxBusinessException;
}
