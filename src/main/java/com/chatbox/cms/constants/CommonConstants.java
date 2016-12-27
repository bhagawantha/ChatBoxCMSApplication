/**
 * 
 * 
 * Description : This class used for defining constants.
 * 
 * @author Bhagawantha Parasuraman
 * @version 1.0
 * @see
 * @since Dec 08, 2016
 */
package com.chatbox.cms.constants;

/**
 * @author Bhagawantha Parasuraman
 *
 */
public interface CommonConstants {

	// JSP View Names
	String HOMEPAGE_VIEW = "cmsHomePage";

	String DETAILED_DISCUSSION_VIEW = "discussionView";

	String ADD_DISUSSION_VIEW = "addDiscussion";

	String EDIT_DISUSSION_VIEW = "discussionEdit";
	
	String VIEW_ALL_DISCUSS = "viewAllDiscussions";

	// Query constants
	String QUERY_FETCH_ALL_TOPIC = "from TopicComment";
	String SQL_QUERY_FETCH_ALL_TOPIC = "Select * from topic";

	// General Constants
	String CLASSPATH_APPLICATION_PROPERTIES = "classpath:application.properties";
	String JPA_REPO_BASE = "com.chatbox.cms.dao";
	String ENTITY_SCAN = "com.chatbox.cms.entity";
	String COMPONENT_SCAN = "com.chatbox.cms.*";
	String SYSTEM = "system";

	String FALLBACK_PAGE = "fallback";

}
