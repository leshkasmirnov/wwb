/**
 * 
 */
package com.wildwestbank.modules.app.gwt.client.views.transactions;

import java.math.BigDecimal;
import java.util.Date;

import com.google.gwt.core.shared.GWT;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.wildwestbank.modules.common.db.model.Transaction;

/**
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public interface TransactionProperties extends PropertyAccess<Transaction> {

	static final TransactionProperties Props = GWT.create(TransactionProperties.class);

	ValueProvider<Transaction, String> transactionTypeAsText();

	ValueProvider<Transaction, String> sourceAccountNumber();

	ValueProvider<Transaction, String> destinationAccountNumber();

	ValueProvider<Transaction, String> personFio();

	ValueProvider<Transaction, String> message();

	ValueProvider<Transaction, Date> transactionDate();

	ValueProvider<Transaction, BigDecimal> summ();
}
