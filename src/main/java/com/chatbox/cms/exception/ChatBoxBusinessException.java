/**
 * Description : This class used for handling business exception.
 * 
 * @author Bhagawantha
 * @version 1.0
 * @see
 * @since Dec 08, 2016
 */

package com.chatbox.cms.exception;

public class ChatBoxBusinessException extends Exception{

	/**
	 * Generated serialVersionUID.
	 */
	private static final long serialVersionUID = 1780947779950194243L;

	/**
	 * The default constructor.
	 */
	public ChatBoxBusinessException() {
		super();
	}

	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public ChatBoxBusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 
	 * @param message
	 */
	public ChatBoxBusinessException(String message) {
		super(message);
	}

	/**
	 * 
	 * @param cause
	 */
	public ChatBoxBusinessException(Throwable cause) {
		super(cause);
	}


}
