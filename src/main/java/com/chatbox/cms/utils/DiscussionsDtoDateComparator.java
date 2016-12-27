/**
 * 
 */
package com.chatbox.cms.utils;

import java.util.Comparator;

import com.chatbox.cms.dto.DiscussionDto;

public class DiscussionsDtoDateComparator implements Comparator<DiscussionDto> {

	@Override
	public int compare(DiscussionDto dissDto1, DiscussionDto dissDto2) {
		return dissDto2.getCreatedDate().compareTo(dissDto1.getCreatedDate());
	}

}
