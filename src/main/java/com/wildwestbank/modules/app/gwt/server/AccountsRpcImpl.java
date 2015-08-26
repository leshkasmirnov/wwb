/**
 * 
 */
package com.wildwestbank.modules.app.gwt.server;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gwt.user.client.rpc.InvocationException;
import com.wildwestbank.modules.app.exceptions.TransactionExecutionException;
import com.wildwestbank.modules.app.gwt.client.rpc.AccountsRpc;
import com.wildwestbank.modules.app.services.AccountsService;
import com.wildwestbank.modules.common.db.dao.AccountsRepository;
import com.wildwestbank.modules.common.db.model.Account;
import com.wildwestbank.modules.common.db.model.Person;
import com.wildwestbank.modules.common.db.model.Transaction;

/**
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
@Service
public class AccountsRpcImpl implements AccountsRpc {

	@Autowired
	private AccountsRepository accountsRepository;

	@Autowired
	private AccountsService accountsService;

	@Override
	public ArrayList<Account> getAll() {
		return (ArrayList<Account>) accountsRepository.findAll();
	}

	@Override
	@Transactional
	public Account save(Account account) {
		return accountsRepository.save(account);
	}

	@Override
	@Transactional
	public void delete(Account account) {
		accountsRepository.delete(account);
	}

	@Override
	public ArrayList<Account> getForPerson(Person person) {
		return (ArrayList<Account>) accountsRepository.findByPerson(person);
	}

	@Override
	public ArrayList<Account> doTransaction(Account account, Transaction transaction) {
		try {
			return (ArrayList<Account>) accountsService.doTransaction(account, transaction);
		} catch (TransactionExecutionException e) {
			throw new InvocationException("Ошибка выполнения транзакции. Подробности: "
					+ e.getMessage(), e);
		}
	}

}
