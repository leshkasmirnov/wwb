/**
 * 
 */
package com.wildwestbank.modules.app.gwt.client.views.accounts;

import java.math.BigDecimal;
import java.util.Date;

import com.google.gwt.core.shared.GWT;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.wildwestbank.modules.common.db.model.Account;

/**
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public interface AccountProperties extends PropertyAccess<Account> {

	static final AccountProperties Props = GWT.create(AccountProperties.class);

	ModelKeyProvider<Account> id();

	ValueProvider<Account, String> accountNumber();

	ValueProvider<Account, String> personAsText();

	ValueProvider<Account, Date> openDate();

	ValueProvider<Account, Date> closeDate();

	ValueProvider<Account, BigDecimal> amount();
}
