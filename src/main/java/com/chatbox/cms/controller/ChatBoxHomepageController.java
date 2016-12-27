/**
 * 
 * 
 * Description : This class used for displaying the homepage and the logged out page views.
 * 
 * @author Bhagawantha
 * @version 1.0
 * @see
 * @since Dec 01, 2016
 */
package com.chatbox.cms.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.chatbox.cms.constants.CommonConstants;
import com.chatbox.cms.dto.CommentsDto;
import com.chatbox.cms.dto.DiscussionDto;
import com.chatbox.cms.dto.UserDto;
import com.chatbox.cms.exception.ChatBoxBusinessException;
import com.chatbox.cms.services.DiscussionForumService;
import com.chatbox.cms.utils.CommentsDtoDateComparator;
import com.chatbox.cms.utils.DiscussionsDtoDateComparator;

/**
 * This class is used for view and handling homepage and loggedout view
 * handling.
 *
 */
@Controller
public class ChatBoxHomepageController {

	/**
	 * Created for viewing Homepage for CMS Application.
	 * 
	 * @return
	 */

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ChatBoxHomepageController.class);

	@Autowired
	DiscussionForumService discussionForumService;

	/**
	 * Loading the homepage.
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/home/", method = { RequestMethod.GET })
	public String viewHomePage(Model model, HttpServletRequest request) {
		System.out.println("Hi! Welcome to CMS Homepage");
		LOGGER.info("Hi! Welcome to CMS Homepage");
		List<DiscussionDto> discussionDtoList = discussionForumService
				.viewBriefDiscussion();
		Collections.sort(discussionDtoList, new DiscussionsDtoDateComparator());
		model.addAttribute("discussions", discussionDtoList);
		return CommonConstants.HOMEPAGE_VIEW;
	}

	/**
	 * Created for viewing Homepage for CMS Application.
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/home/viewDiscussion/{id}", method = RequestMethod.GET)
	public ModelAndView viewDiscussion(Model model, HttpServletRequest request,
			HttpServletResponse response, @PathVariable("id") int discussId)
			throws IOException {
		System.out
				.println("Hi! Displaying Full Discussion In system for discussionId "
						+ discussId);
		LOGGER.info("Hi! Displaying Full Discussion In system for discussionId "
				+ discussId);
		List<CommentsDto> commentsDtoList = getComments(model, discussId);
		Collections.sort(commentsDtoList, new CommentsDtoDateComparator());
		String rootPath = getRootPath(request);
		model.addAttribute("baseUrl", rootPath);
		model.addAttribute("comments", commentsDtoList);
		return new ModelAndView(CommonConstants.DETAILED_DISCUSSION_VIEW);
	}

	/**
	 * @param request
	 * @return
	 */
	private String getRootPath(HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		return url
				.substring(0, url.length() - request.getRequestURI().length())
				+ request.getContextPath();
	}

	/**
	 * @param model
	 * @param discussId
	 * @return
	 */
	private List<CommentsDto> getComments(Model model, int discussId) {
		DiscussionDto discussionDto = discussionForumService
				.getDiscussionById(discussId);
		model.addAttribute("discussionDto", discussionDto);
		List<CommentsDto> commentsDtoList = discussionDto.getComments();
		return commentsDtoList;
	}

	/**
	 * Store comments related to the discussion.
	 * 
	 * @param model
	 * @param request
	 * @param discussionId
	 * @param comment
	 * @return
	 */
	@RequestMapping(value = "/home/postComment", method = RequestMethod.POST)
	public String postComment(Model model, HttpServletRequest request,
			Principal user) {
		List<CommentsDto> commentList;
		try {
			String discussionId = request.getParameter("discussionId");
			String comment = request.getParameter("comment");
			LOGGER.info("Storing comments to the discussion");
			System.out.println("Storing comments to the discussion");
			int discussionIdInt = Integer.parseInt(discussionId);
			String userName = fetchAuthName(user);

			commentList = discussionForumService.storeComment(discussionIdInt,
					comment, userName);

			commentList.addAll(getComments(model, discussionIdInt));
			Collections.sort(commentList, new CommentsDtoDateComparator());
			model.addAttribute("comments", commentList);
		} catch (ChatBoxBusinessException e) {
			LOGGER.error(":: postComment :: exception :: ", e);
		}
		return "commentsView";
	}

	/**
	 * @param user
	 * @return
	 */
	private String fetchAuthName(Principal user) {
		String userName = CommonConstants.SYSTEM;
		if (null != user && !user.getName().isEmpty()) {
			userName = user.getName();
		}
		return userName;
	}

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/chatlogin/", method = RequestMethod.GET)
	public ModelAndView login(Model model, HttpServletRequest request) {
		LOGGER.info("login to  continue");
		System.out.println("login to  continue");
		String referrer = request.getHeader("Referer");
		request.getSession().setAttribute("url_prior_login", referrer);
		model.addAttribute("loginBean", new UserDto());
		return new ModelAndView("login");
	}

	/**
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/home/viewAllDiscussion", method = { RequestMethod.GET })
	public String viewAllDiscussion(Model model, HttpServletRequest request) {
		System.out.println("Hi! Welcome to CMS View All Discussions");
		LOGGER.info("Hi! Welcome to CMS  View All Discussions");
		List<DiscussionDto> discussionDtoList = discussionForumService
				.viewBriefDiscussion();
		Collections.sort(discussionDtoList, new DiscussionsDtoDateComparator());
		model.addAttribute("discussions", discussionDtoList);
		return CommonConstants.VIEW_ALL_DISCUSS;
	}

	/**
	 * @return the discussionForumService
	 */
	public DiscussionForumService getDiscussionForumService() {
		return discussionForumService;
	}

	/**
	 * @param discussionForumService
	 *            the discussionForumService to set
	 */
	public void setDiscussionForumService(
			DiscussionForumService discussionForumService) {
		this.discussionForumService = discussionForumService;
	}

}
