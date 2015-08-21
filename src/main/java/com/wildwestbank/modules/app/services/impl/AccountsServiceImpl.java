/**
 * 
 */
package com.wildwestbank.modules.app.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wildwestbank.modules.app.internal.TransactionTypes;
import com.wildwestbank.modules.app.services.AccountsService;
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
	public List<Account> doTransaction(Account account, Transaction transaction) {
		BigDecimal current = account.getAmount();
		transaction.setTransactionDate(new Date());
		transaction.setSourceAccountNumber(account.getAccountNumber());
		SecurityContext sc = (SecurityContext) RemoteServiceRequestResponseHolder
				.getHttpServletRequest().getSession().getAttribute(SecurityContext.class.getName());
		transaction.setUserId(1);
		List<Account> result = new ArrayList<>(2);
		if (TransactionTypes.UP.getValue() == transaction.getTransactionType()) {
			account.setAmount(current.add(transaction.getSumm()));
			result.add(accountsRepository.save(account));
			transactionsRepository.save(transaction);
		} else if (TransactionTypes.DOWN.getValue() == transaction.getTransactionType()) {
			account.setAmount(current.subtract(transaction.getSumm()));
			result.add(accountsRepository.save(account));
			transactionsRepository.save(transaction);
		} else if (TransactionTypes.TRANSFER.getValue() == transaction.getTransactionType()) {
			Account dest = accountsRepository.findByAccountNumber(transaction
					.getDestinationAccountNumber());
			if (dest != null) {
				account.setAmount(current.subtract(transaction.getSumm()));
				dest.setAmount(dest.getAmount().add(transaction.getSumm()));
				result.add(accountsRepository.save(account));
				result.add(accountsRepository.save(dest));
				transactionsRepository.save(transaction);
			}
		} else {
			throw new RuntimeException("Unknown transactionType");
		}
		return result;
	}
}
