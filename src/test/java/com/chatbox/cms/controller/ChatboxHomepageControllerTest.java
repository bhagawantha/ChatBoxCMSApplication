/**
 * 
 */
package com.chatbox.cms.controller;

import javax.servlet.http.HttpServletRequest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

import com.chatbox.cms.services.DiscussionForumService;

/**
 * @author Bhagawantha Parasuraman
 *
 */
public class ChatboxHomepageControllerTest {

	ChatBoxHomepageController controller = new ChatBoxHomepageController();
	Model model;
	HttpServletRequest request;
	DiscussionForumService discussionForumService;

	@Before
	public void setUp() {
		model = Mockito.mock(Model.class);
		request = Mockito.mock(HttpServletRequest.class);
		discussionForumService = Mockito.mock(DiscussionForumService.class);
	}

	@After
	public void tearDown() {
		controller = null;
	}

	@Test
	public void loginTest() {
		Assert.assertNotNull(controller.login(model,request));
	}

	@Test
	public void homeTest() {
		controller.setDiscussionForumService(discussionForumService);
		Assert.assertNotNull(controller.viewHomePage(model, request));
	}
}
