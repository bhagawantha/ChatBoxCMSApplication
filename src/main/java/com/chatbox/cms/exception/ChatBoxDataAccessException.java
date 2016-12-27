/**
 * Description : This class used for handling DAO layer exception.
 * 
 * @author Bhagawantha
 * @version 1.0
 * @see
 * @since Dec 08, 2016
 */

package com.chatbox.cms.exception;

public class ChatBoxDataAccessException extends Exception{


	/**
	 * Generated serial Version Id.
	 */
	private static final long serialVersionUID = 5836157773020759727L;

	/**
	 * The default constructor 
	 */
	public ChatBoxDataAccessException() {
		super();
	}

	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public ChatBoxDataAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public ChatBoxDataAccessException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ChatBoxDataAccessException(Throwable cause) {
		super(cause);
	}
}
