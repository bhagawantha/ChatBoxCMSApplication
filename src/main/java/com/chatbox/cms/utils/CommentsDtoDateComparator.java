/**
 * 
 */
package com.chatbox.cms.utils;

import java.util.Comparator;

import com.chatbox.cms.dto.CommentsDto;

public class CommentsDtoDateComparator implements Comparator<CommentsDto> {

	@Override
	public int compare(CommentsDto o1, CommentsDto o2) {
		return o2.getCreatedDate().compareTo(o1.getCreatedDate());
	}

}
