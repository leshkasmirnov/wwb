/**
 * 
 */
package com.wildwestbank.modules.app.gwt.client.rpc;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.wildwestbank.modules.common.db.model.Account;
import com.wildwestbank.modules.common.db.model.Person;
import com.wildwestbank.modules.common.db.model.Transaction;

/**
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public interface AccountsRpcAsync {

	void getAll(AsyncCallback<ArrayList<Account>> callback);

	void getForPerson(Person person, AsyncCallback<ArrayList<Account>> callback);

	void save(Account account, AsyncCallback<Account> callback);

	void delete(Account account, AsyncCallback<Void> callback);

	void doTransaction(Account account, Transaction transaction,
			AsyncCallback<ArrayList<Account>> callback);
}
