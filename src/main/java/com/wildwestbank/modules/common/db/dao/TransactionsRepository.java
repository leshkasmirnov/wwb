/**
 * 
 */
package com.wildwestbank.modules.common.db.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.wildwestbank.modules.common.db.model.Transaction;

/**
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public interface TransactionsRepository extends CrudRepository<Transaction, Integer> {

	@Query("select new Transaction(t, person) from Transaction t, Person person where person in (select account.person from Account account where account.accountNumber = t.sourceAccountNumber)")
	ArrayList<Transaction> getAllWithPersonFio();
}
