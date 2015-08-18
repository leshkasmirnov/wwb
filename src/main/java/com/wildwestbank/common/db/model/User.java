/**
 * 
 */
package com.wildwestbank.common.db.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Сущность пользователя.
 * 
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
@Entity
@Table(name = "users")
public class User extends BaseModel {
	private static final long serialVersionUID = -8967250129126331785L;

	private String name;
	private String password;
	private Boolean active;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}
