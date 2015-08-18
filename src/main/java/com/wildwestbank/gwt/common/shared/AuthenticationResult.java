/**
 * 
 */
package com.wildwestbank.gwt.common.shared;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Результат аутентификации.
 * 
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public class AuthenticationResult implements IsSerializable, Serializable {

	private static final long serialVersionUID = -8343306975051906855L;

	public static enum AuthenticationState implements IsSerializable {
		OK, ERROR;
	}

	private AuthenticationState state;
	private String message;

	public AuthenticationResult() {
		super();
	}

	public AuthenticationResult(AuthenticationState state) {
		super();
		this.state = state;
	}

	public AuthenticationResult(AuthenticationState state, String message) {
		super();
		this.state = state;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public AuthenticationState getState() {
		return state;
	}

	public void setState(AuthenticationState state) {
		this.state = state;
	}

}
