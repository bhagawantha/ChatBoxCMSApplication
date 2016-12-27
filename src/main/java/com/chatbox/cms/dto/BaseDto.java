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

import java.util.Date;


public class BaseDto {

	private String createdBy;
	
	private String updatedBy;
	
	private Date createdDate;
	
	private Date modifiedDate;

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	
}
