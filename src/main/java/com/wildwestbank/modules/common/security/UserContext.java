/**
 * 
 */
package com.wildwestbank.modules.common.security;

import java.io.Serializable;

/**
 * Контекст пользователя.
 * 
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public class UserContext implements Serializable {

	private static final long serialVersionUID = -867413386769121743L;
	private Integer userId;
	private String login;
	private String password;
	private boolean superUser;
	private String fio;

	public UserContext(String login, String password, Integer userId, boolean superUser) {
		super();
		this.login = login;
		this.password = password;
		this.userId = userId;
		this.superUser = superUser;
	}

	public UserContext() {
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public Integer getUserId() {
		return userId;
	}

	public boolean isSuperUser() {
		return superUser;
	}

	public String getFio() {
		return fio;
	}

	public void setFio(String fio) {
		this.fio = fio;
	}
}
