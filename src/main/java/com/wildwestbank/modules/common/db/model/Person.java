/**
 * 
 */
package com.wildwestbank.modules.common.db.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

/**
 * Сущность физ. лицо.
 * 
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person implements Identified, Serializable {

	private static final long serialVersionUID = 299065720661163465L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "persons_sequence")
	@SequenceGenerator(sequenceName = "persons_sequence", name = "persons_sequence", allocationSize = 1)
	private Integer id;

	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "middle_name")
	private String middleName;
	private Date birthday;
	private String inn;
	private String email;
	private String phone;
	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;
	@Transient
	private String fio;
	@Transient
	private String addressAsText;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getInn() {
		return inn;
	}

	public void setInn(String inn) {
		this.inn = inn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getFio() {
		return firstName != null && lastName != null ? firstName + " " + lastName + " "
				+ middleName : null;
	}

	public String getAddressAsText() {
		return address != null ? address.asText() : null;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

}
