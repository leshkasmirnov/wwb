/**
 * 
 */
package com.wildwestbank.modules.app.services;

import java.util.List;

import com.wildwestbank.modules.app.exceptions.TransactionExecutionException;
import com.wildwestbank.modules.common.db.model.Account;
import com.wildwestbank.modules.common.db.model.Transaction;

/**
 * Исполнитель транзакций.
 * 
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public interface TransactionExecutor {

	List<Account> execute(Transaction transaction, Integer userId)
			throws TransactionExecutionException;
}
