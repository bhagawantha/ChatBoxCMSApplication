/**
 * 
 * 
 * Description : This class used for setting the Hibernate session factory
 * 
 * @author Bhagawantha
 * @version 1.0
 * @see
 * @since Dec 08, 2016
 */
package com.chatbox.cms.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.chatbox.cms.constants.CommonConstants;
import com.chatbox.cms.dto.DiscussionDto;
import com.chatbox.cms.exception.ChatBoxBusinessException;
import com.chatbox.cms.services.DiscussionForumService;
import com.chatbox.cms.utils.FileHandlerUtil;

@Controller
@RequestMapping("/admin")
public class AdminDiscussionsController {

	/**
	 * Created for Uploading a discussion for CMS Application.
	 * 
	 * @return
	 */

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AdminDiscussionsController.class);

	@Autowired
	DiscussionForumService discussionForumService;

	@Autowired
	FileHandlerUtil fileHandlerUtil;

	@RequestMapping(value = "/addDiscussion", method = { RequestMethod.GET })
	public ModelAndView addDiscussion(Model model) {
		System.out.println("Create a new discussion item in system ");
		LOGGER.info("Create a new discussion item in system ");
		List<MultipartFile> attachments = new ArrayList<MultipartFile>();
		 DiscussionDto disccusionDto = new DiscussionDto();
		 disccusionDto.setMode("Add");
		model.addAttribute("discussionDto",disccusionDto);
		return new ModelAndView(CommonConstants.ADD_DISUSSION_VIEW);
	}

	@RequestMapping(value = "/storeDiscussion", method = { RequestMethod.POST,
			RequestMethod.GET })
	public String storeDiscussion(Model model, HttpServletRequest request,
			Principal principal,
			@ModelAttribute("discussionDto") DiscussionDto discussionDto) {
		try {
			System.out
					.println("Storing new discussion item in system :: discussionDto :: "
							+ discussionDto.toString());
			LOGGER.info("Storing new discussion item in system :: discussionDto :: "
					+ discussionDto.toString());
			List<MultipartFile> cmsFiles = discussionDto.getAttachments();
			List<String> fileLocations = new ArrayList<String>();
			String rootPath = request.getSession().getServletContext()
					.getRealPath("/");
			fileLocations = fileHandlerUtil.storeDocuments(cmsFiles,
					fileLocations, rootPath);

			Date date = new Date();
			discussionDto.setCreatedBy(fetchAuthName(principal));
			discussionDto.setFileLocation(fileLocations);

			discussionDto = discussionForumService
					.storeDiscussion(discussionDto);
			discussionDto.setFileLocation(fileLocations);
			 model.addAttribute("baseUrl",rootPath);
			if(discussionDto.getMode().equalsIgnoreCase("Add")){
				model.addAttribute("successMessage","Dicussion stored successfully !!");
			}else{
				model.addAttribute("successMessage","Edited Dicussion stored successfully !!");
			}
		} catch (ChatBoxBusinessException e) {
			LOGGER.error(":: storeDiscussion :: excep", e);
		}
		String target = null;
		if(discussionDto.getMode().equalsIgnoreCase("ADD")){
			target = CommonConstants.ADD_DISUSSION_VIEW;
		}else{
			target = CommonConstants.DETAILED_DISCUSSION_VIEW;
		}
		return target;
	}

	/**
	 * Created for Viewing Discussion for CMS Application.
	 * 
	 * @return
	 */
	@RequestMapping(value = "/editDiscussion", method = { RequestMethod.POST })
	public String editDiscussion(Model model, HttpServletRequest request,
			@RequestBody String discussn) {
		System.out.println("Editing existing discussion in system");
		LOGGER.info("Editing existing discussion in system");
		String discussionId = discussn.split("discussionId=")[1];
		DiscussionDto discussionDto = discussionForumService
				.getDiscussionById(Integer.parseInt(discussionId));
		discussionDto.setMode("Edit");
		model.addAttribute("discussionDto", discussionDto);
		return CommonConstants.ADD_DISUSSION_VIEW;

	}

	/**
	 * Created for deleting Discussion for CMS Application.
	 * 
	 * @return
	 */
	@RequestMapping("/deleteDiscussion")
	public String deleteDiscussion(Model model, HttpServletRequest request) {
		try {
			String discussionId = request.getParameter("discussionId");
			System.out
					.println("Deleting discussion from the system , discussionId = "
							+ discussionId);
			LOGGER.info("Deleting discussion from the system, discussionId = "
					+ discussionId);

			discussionForumService.removeDiscussion(Integer
					.parseInt(discussionId));
		} catch (ChatBoxBusinessException e) {
			LOGGER.error(":: deleteDiscussion :: excep", e);
		}
		return CommonConstants.HOMEPAGE_VIEW;
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
	 * @param request
	 * @return
	 */
	private String getRootPath(HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		return url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath();
	}

}
