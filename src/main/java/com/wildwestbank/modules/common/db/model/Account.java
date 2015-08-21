/**
 * 
 */
package com.wildwestbank.modules.common.db.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

/**
 * Сущность счет.
 * 
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
@Entity
public class Account implements Identified, Serializable {

	private static final long serialVersionUID = -851052275773265051L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accounts_sequence")
	@SequenceGenerator(sequenceName = "accounts_sequence", name = "accounts_sequence", allocationSize = 1)
	private Integer id;

	@Column(name = "account_number")
	private String accountNumber;
	@JoinColumn(name = "person_id")
	@OneToOne
	private Person person;
	@Column(name = "open_date")
	private Date openDate;
	@Column(name = "close_date")
	private Date closeDate;
	private BigDecimal amount;
	@Transient
	private String personAsText;

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getPersonAsText() {
		return person != null ? person.getFio() : null;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

}
