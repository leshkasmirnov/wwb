/**
 * 
 */
package com.wildwestbank.modules.app.gwt.client.views.accounts;

import com.sencha.gxt.widget.core.client.Dialog;
import com.wildwestbank.modules.app.gwt.client.views.BeanEditor;
import com.wildwestbank.modules.common.db.model.Account;
import com.wildwestbank.modules.common.db.model.Transaction;

/**
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public class AccountOperationsDialog extends Dialog implements BeanEditor<Transaction> {

	private AccountsOperationsWidget accountsOperationsWidget;

	public AccountOperationsDialog(Account account) {
		super();
		setHeadingHtml("Операции над счетом № " + account.getAccountNumber());
		setWidth("40%");
		accountsOperationsWidget = new AccountsOperationsWidget();
		add(accountsOperationsWidget);
	}

	@Override
	public void init(Transaction bean) {
		accountsOperationsWidget.init(bean);
	}

	@Override
	public Transaction flush() {
		return accountsOperationsWidget.flush();
	}

	@Override
	public boolean validate() {
		return accountsOperationsWidget.validate();
	}

}
