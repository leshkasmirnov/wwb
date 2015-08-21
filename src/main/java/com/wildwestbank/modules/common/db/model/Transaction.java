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
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import com.wildwestbank.modules.app.internal.TransactionTypes;

/**
 * Сущность Транзакция.
 * 
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
@Entity
public class Transaction implements Identified, Serializable {

	private static final long serialVersionUID = 440211235721317015L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transactions_sequence")
	@SequenceGenerator(sequenceName = "transactions_sequence", name = "transactions_sequence", allocationSize = 1)
	private Integer id;

	@Column(name = "transaction_type")
	private Integer transactionType;
	private BigDecimal summ;
	@Column(name = "source_account_number")
	private String sourceAccountNumber;
	@Column(name = "destination_account_number")
	private String destinationAccountNumber;
	private String message;
	@Column(name = "transaction_date")
	private Date transactionDate;
	@Column(name = "user_id")
	private Integer userId;
	@Transient
	private String transactionTypeAsText;
	@Transient
	private String personFio;

	public Transaction() {
		super();
	}

	public Transaction(Transaction t, Person person) {
		super();
		this.personFio = person.getFio();
		this.id = t.getId();
		this.transactionDate = t.getTransactionDate();
		this.transactionType = t.getTransactionType();
		this.summ = t.getSumm();
		this.sourceAccountNumber = t.getSourceAccountNumber();
		this.destinationAccountNumber = t.getDestinationAccountNumber();
		this.message = t.getMessage();
		this.userId = t.getUserId();
	}

	public Integer getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(Integer transactionType) {
		this.transactionType = transactionType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public BigDecimal getSumm() {
		return summ;
	}

	public void setSumm(BigDecimal summ) {
		this.summ = summ;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public String getDestinationAccountNumber() {
		return destinationAccountNumber;
	}

	public void setDestinationAccountNumber(String destinationAccountNumber) {
		this.destinationAccountNumber = destinationAccountNumber;
	}

	public String getTransactionTypeAsText() {
		if (TransactionTypes.UP.getValue() == transactionType) {
			return TransactionTypes.UP.getDescription();
		} else if (TransactionTypes.DOWN.getValue() == transactionType) {
			return TransactionTypes.DOWN.getDescription();
		} else if (TransactionTypes.TRANSFER.getValue() == transactionType) {
			return TransactionTypes.TRANSFER.getDescription();
		}
		return null;
	}

	public String getSourceAccountNumber() {
		return sourceAccountNumber;
	}

	public void setSourceAccountNumber(String sourceAccountNumber) {
		this.sourceAccountNumber = sourceAccountNumber;
	}

	public String getPersonFio() {
		return personFio;
	}

	public void setPersonFio(String personFio) {
		this.personFio = personFio;
	}

}
