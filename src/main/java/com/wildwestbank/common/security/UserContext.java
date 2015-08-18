/**
 * 
 */
package com.wildwestbank.common.security;

/**
 * Контекст пользователя.
 * 
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public class UserContext {

	private final String login;
	private final String password;

	public UserContext(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}
}
