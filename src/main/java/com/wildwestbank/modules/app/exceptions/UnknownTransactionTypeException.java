/**
 * 
 */
package com.wildwestbank.modules.app.exceptions;

/**
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public class UnknownTransactionTypeException extends RuntimeException {

	private static final long serialVersionUID = -5741649610484424958L;

	public UnknownTransactionTypeException() {
		super();
	}

	public UnknownTransactionTypeException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UnknownTransactionTypeException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnknownTransactionTypeException(String message) {
		super(message);
	}

	public UnknownTransactionTypeException(Throwable cause) {
		super(cause);
	}

}
