/**
 * 
 */
package com.wildwestbank.modules.app.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wildwestbank.modules.app.exceptions.TransactionExecutionException;
import com.wildwestbank.modules.app.exceptions.UnknownTransactionTypeException;
import com.wildwestbank.modules.app.internal.TransactionTypes;
import com.wildwestbank.modules.app.services.TransactionExecutor;
import com.wildwestbank.modules.common.db.dao.AccountsRepository;
import com.wildwestbank.modules.common.db.dao.TransactionsRepository;
import com.wildwestbank.modules.common.db.model.Account;
import com.wildwestbank.modules.common.db.model.Transaction;

/**
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
@Service
public class TransactionExecutorImpl implements TransactionExecutor {

	@Autowired
	private AccountsRepository accountsRepository;

	@Autowired
	private TransactionsRepository transactionsRepository;

	private volatile Set<String> lockedAccounts = new HashSet<>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wildwestbank.modules.app.services.TransactionExecutor#execute(com
	 * .wildwestbank.modules.common.db.model.Transaction)
	 */
	@Override
	@Transactional
	public List<Account> execute(Transaction transaction, Integer userId)
			throws TransactionExecutionException {
		List<Account> result = null;
		if (setLockedIfFree(transaction.getSourceAccountNumber(),
				transaction.getDestinationAccountNumber())) {
			result = doTransaction(transaction, userId);
			releaseAccounts(transaction.getSourceAccountNumber(),
					transaction.getDestinationAccountNumber());
		} else {
			try {
				Thread.sleep(100);
				execute(transaction, userId);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * Непосредственно выполнить транзакцию.
	 * 
	 * @param transaction
	 * @return
	 * @throws TransactionExecutionException
	 */
	private List<Account> doTransaction(Transaction transaction, Integer userId)
			throws TransactionExecutionException {
		List<Account> result;
		transaction.setTransactionDate(new Date());
		transaction.setUserId(userId);

		if (TransactionTypes.UP.getValue() == transaction.getTransactionType()) {
			Account sourceAccount = accountsRepository.findByAccountNumber(transaction
					.getSourceAccountNumber());
			result = upOperation(sourceAccount, transaction);
		} else if (TransactionTypes.DOWN.getValue() == transaction.getTransactionType()) {
			Account sourceAccount = accountsRepository.findByAccountNumber(transaction
					.getSourceAccountNumber());
			result = downOperation(sourceAccount, transaction);
		} else if (TransactionTypes.TRANSFER.getValue() == transaction.getTransactionType()) {
			Account sourceAccount = accountsRepository.findByAccountNumber(transaction
					.getSourceAccountNumber());
			Account dest = accountsRepository.findByAccountNumber(transaction
					.getDestinationAccountNumber());
			result = transferOperation(sourceAccount, dest, transaction);
		} else {
			throw new UnknownTransactionTypeException("Unknown transactionType "
					+ transaction.getTransactionType());
		}
		return result;
	}

	private List<Account> upOperation(Account sourceAccount, Transaction transaction) {
		List<Account> result = new ArrayList<>(1);
		BigDecimal current = sourceAccount.getAmount();
		sourceAccount.setAmount(current.add(transaction.getSumm()));
		sourceAccount = accountsRepository.save(sourceAccount);
		transactionsRepository.save(transaction);
		result.add(sourceAccount);
		return result;
	}

	private List<Account> downOperation(Account sourceAccount, Transaction transaction)
			throws TransactionExecutionException {
		List<Account> result = new ArrayList<>(1);
		BigDecimal current = sourceAccount.getAmount();
		sourceAccount.setAmount(current.subtract(transaction.getSumm()));
		if (sourceAccount.getAmount().intValue() < 0) {
			throw new TransactionExecutionException("У счета не может быть отрицательный баланс!");
		}
		sourceAccount = accountsRepository.save(sourceAccount);
		transactionsRepository.save(transaction);
		result.add(sourceAccount);
		return result;
	}

	private List<Account> transferOperation(Account sourceAccount, Account dest,
			Transaction transaction) throws TransactionExecutionException {
		List<Account> result = new ArrayList<>(2);
		BigDecimal current = sourceAccount.getAmount();
		sourceAccount.setAmount(current.subtract(transaction.getSumm()));
		if (sourceAccount.getAmount().intValue() < 0) {
			throw new TransactionExecutionException("У счета не может быть отрицательный баланс!");
		}
		dest.setAmount(dest.getAmount().add(transaction.getSumm()));
		result.add(accountsRepository.save(sourceAccount));
		result.add(accountsRepository.save(dest));
		transactionsRepository.save(transaction);
		return result;
	}

	/**
	 * Метод преверяет, заблокированы ли для в данный момент счета. Если не
	 * заблокированы, то блокирует все.
	 * 
	 * @param accountNumbers
	 *            номера счетов.
	 * 
	 * @return true если удалось заблокировать и false, если счета уже были
	 *         заблокированы другим потоком.
	 */
	private boolean setLockedIfFree(String... accountNumbers) {
		synchronized (lockedAccounts) {
			boolean locked = false;
			for (String accountNumber : accountNumbers) {
				if (accountNumber != null && lockedAccounts.contains(accountNumber)) {
					locked = true;
					break;
				}
			}
			if (!locked) {
				for (String accountNumber : accountNumbers) {
					if (accountNumber != null) {
						lockedAccounts.add(accountNumber);
						break;
					}
				}
			}
			return !locked;
		}
	}

	/**
	 * Освободить от блокировки счета.
	 * 
	 * @param accountNumbers
	 */
	private void releaseAccounts(String... accountNumbers) {
		synchronized (lockedAccounts) {
			for (String accountNumber : accountNumbers) {
				if (accountNumber != null) {
					lockedAccounts.remove(accountNumber);
				}
			}
		}
	}
}
