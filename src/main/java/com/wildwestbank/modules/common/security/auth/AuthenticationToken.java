/**
 * 
 */
package com.wildwestbank.modules.common.security.auth;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Объект аутентификации.
 * 
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public class AuthenticationToken implements IsSerializable, Serializable {

	private static final long serialVersionUID = 3504499124276875563L;

	private String userName;
	private String password;

	public AuthenticationToken() {
		super();
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
