/**
 * 
 */
package com.wildwestbank.common.security;

/**
 * Контекст безопасности.
 * 
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public class SecurityContext {

	private UserContext userContext;

	public UserContext getUserContext() {
		return userContext;
	}

	public void setUserContext(UserContext userContext) {
		this.userContext = userContext;
	}
}
