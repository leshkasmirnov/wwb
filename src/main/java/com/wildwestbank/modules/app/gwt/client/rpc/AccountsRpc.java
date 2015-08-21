/**
 * 
 */
package com.wildwestbank.modules.app.gwt.client.rpc;

import java.util.ArrayList;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.wildwestbank.modules.common.db.model.Account;
import com.wildwestbank.modules.common.db.model.Person;
import com.wildwestbank.modules.common.db.model.Transaction;

/**
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
@RemoteServiceRelativePath("AccountsRpc.rpc")
public interface AccountsRpc extends RemoteService {

	static final AccountsRpcAsync Async = (AccountsRpcAsync) (GWT.isClient() ? GWT
			.create(AccountsRpc.class) : null);

	ArrayList<Account> getAll();

	ArrayList<Account> getForPerson(Person person);

	Account save(Account account);

	void delete(Account account);

	ArrayList<Account> doTransaction(Account account, Transaction transaction);
}
