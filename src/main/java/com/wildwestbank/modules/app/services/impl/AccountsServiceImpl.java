/**
 * 
 */
package com.wildwestbank.modules.app.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wildwestbank.modules.app.exceptions.TransactionExecutionException;
import com.wildwestbank.modules.app.services.AccountsService;
import com.wildwestbank.modules.app.services.TransactionExecutor;
import com.wildwestbank.modules.common.db.dao.AccountsRepository;
import com.wildwestbank.modules.common.db.dao.TransactionsRepository;
import com.wildwestbank.modules.common.db.model.Account;
import com.wildwestbank.modules.common.db.model.Transaction;
import com.wildwestbank.modules.common.security.SecurityContext;
import com.wildwestbank.spring.RemoteServiceRequestResponseHolder;

/**
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
@Service
public class AccountsServiceImpl implements AccountsService {

	@Autowired
	private AccountsRepository accountsRepository;

	@Autowired
	private TransactionsRepository transactionsRepository;

	@Autowired
	private TransactionExecutor transactionExecutor;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wildwestbank.modules.app.services.AccountsService#doTransaction(com
	 * .wildwestbank.modules.common.db.model.Account,
	 * com.wildwestbank.modules.common.db.model.Transaction)
	 */
	@Transactional
	@Override
	public List<Account> doTransaction(Account account, Transaction transaction)
			throws TransactionExecutionException {
		SecurityContext sc = (SecurityContext) RemoteServiceRequestResponseHolder
				.getHttpServletRequest().getSession().getAttribute(SecurityContext.class.getName());
		transaction.setSourceAccountNumber(account.getAccountNumber());
		List<Account> result = transactionExecutor.execute(transaction, sc.getUserContext()
				.getUserId());
		return result;
	}
}
