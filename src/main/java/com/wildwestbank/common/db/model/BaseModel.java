/**
 * 
 */
package com.wildwestbank.common.db.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
@MappedSuperclass
public class BaseModel implements Serializable {

	private static final long serialVersionUID = 2725361368338095243L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
