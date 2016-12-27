/**
 * 
 */
package com.chatbox.cms.services;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

import com.chatbox.cms.dao.DiscussionDao;
import com.chatbox.cms.dto.DiscussionDto;
import com.chatbox.cms.exception.ChatBoxBusinessException;
import com.chatbox.cms.exception.ChatBoxDataAccessException;

/**
 * @author Bhagawantha Parasuraman
 *
 */
public class DiscussionForumServiceTest {

	DiscussionForumServiceImpl discussionForumService = new DiscussionForumServiceImpl();
	Model model;
	HttpServletRequest request;
	DiscussionDao discussionDao;

	@Before
	public void setUp() {
		model = Mockito.mock(Model.class);
		request = Mockito.mock(HttpServletRequest.class);
		discussionDao = Mockito.mock(DiscussionDao.class);
		discussionForumService.setDiscussionDao(discussionDao);
	}

	@After
	public void tearDown() {

	}

	@Test
	public void viewBriefDiscussionTest() {
		Mockito.when(discussionDao.getAllTopics()).thenReturn(
				new ArrayList<DiscussionDto>());
		Assert.assertNotNull(discussionForumService.viewBriefDiscussion());

	}


	@Test
	public void storeDiscussionTest() throws ChatBoxBusinessException {
		DiscussionDto discussionDto = new DiscussionDto();
		discussionForumService.storeDiscussion(discussionDto);
	}

	@Test
	public void removeDiscussionTest() throws ChatBoxBusinessException {
		discussionForumService.removeDiscussion(20);
	}

	@Test
	public void getDiscussionByIdTest() {
		discussionForumService.getDiscussionById(20);
	}

	@Test
	public void storeCommentTest() throws ChatBoxBusinessException {
		discussionForumService.storeComment(20, "comment", "usrName");
	}

}
