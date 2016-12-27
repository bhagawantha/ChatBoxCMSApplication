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

package com.chatbox.cms.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;


public class DiscussionDto extends BaseDto{
	
	private int discussionId;
	
	private String topic;
	
	private String description;

	private List<MultipartFile> attachments;
	
	private List<String> fileLocation;
	
	private List<String> fileType;

	private List<CommentsDto> comments;
	
	private String mode;
	
	private int commentsCount;
	
	public int getDiscussionId() {
		return discussionId;
	}

	public void setDiscussionId(int i) {
		this.discussionId = i;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<MultipartFile> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<MultipartFile> attachments) {
		this.attachments = attachments;
	}

	public List<CommentsDto> getComments() {
		return comments;
	}

	public void setComments(List<CommentsDto> comments) {
		this.comments = comments;
	}

	/**
	 * @return the fileLocation
	 */
	public List<String> getFileLocation() {
		return fileLocation;
	}

	/**
	 * @param fileLocation the fileLocation to set
	 */
	public void setFileLocation(List<String> fileLocation) {
		this.fileLocation = fileLocation;
	}

	/**
	 * @return the fileType
	 */
	public List<String> getFileType() {
		return fileType;
	}

	/**
	 * @param fileType the fileType to set
	 */
	public void setFileType(List<String> fileType) {
		this.fileType = fileType;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final int maxLen = 2;
		return "DiscussionDto [discussionId="
				+ discussionId
				+ ", "
				+ (topic != null ? "topic=" + topic + ", " : "")
				+ (description != null ? "description=" + description + ", "
						: "")
				+ (attachments != null ? "attachments="
						+ attachments.subList(0,
								Math.min(attachments.size(), maxLen)) + ", "
						: "")
				+ (fileLocation != null ? "fileLocation="
						+ fileLocation.subList(0,
								Math.min(fileLocation.size(), maxLen)) + ", "
						: "")
				+ (fileType != null ? "fileType="
						+ fileType
								.subList(0, Math.min(fileType.size(), maxLen))
						+ ", " : "")
				+ (comments != null ? "comments="
						+ comments
								.subList(0, Math.min(comments.size(), maxLen))
						: "") + "]";
	}

	/**
	 * @return the commentsCount
	 */
	public int getCommentsCount() {
		return commentsCount;
	}

	/**
	 * @param commentsCount the commentsCount to set
	 */
	public void setCommentsCount(int commentsCount) {
		this.commentsCount = commentsCount;
	}

	/**
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * @param mode the mode to set
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}
	


}
