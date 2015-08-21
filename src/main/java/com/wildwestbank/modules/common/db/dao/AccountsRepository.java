/**
 * 
 */
package com.wildwestbank.modules.common.db.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.wildwestbank.modules.common.db.model.Account;
import com.wildwestbank.modules.common.db.model.Person;

/**
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public interface AccountsRepository extends CrudRepository<Account, Integer> {

	List<Account> findByPerson(Person person);

	Account findByAccountNumber(String number);
}
