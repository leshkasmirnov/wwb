/**
 * 
 */
package com.wildwestbank.modules.app.services;

import java.util.List;

import com.wildwestbank.modules.app.exceptions.TransactionExecutionException;
import com.wildwestbank.modules.common.db.model.Account;
import com.wildwestbank.modules.common.db.model.Transaction;

/**
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public interface AccountsService {

	List<Account> doTransaction(Account account, Transaction transaction)
			throws TransactionExecutionException;
}
