/**
 * 
 */
package com.wildwestbank.modules.app.exceptions;

/**
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public class TransactionExecutionException extends Exception {

	private static final long serialVersionUID = 4808437332484321208L;

	public TransactionExecutionException() {
		super();
	}

	public TransactionExecutionException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TransactionExecutionException(String message, Throwable cause) {
		super(message, cause);
	}

	public TransactionExecutionException(String message) {
		super(message);
	}

	public TransactionExecutionException(Throwable cause) {
		super(cause);
	}

}
