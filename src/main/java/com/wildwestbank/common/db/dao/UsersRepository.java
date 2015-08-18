/**
 * 
 */
package com.wildwestbank.common.db.dao;

import org.springframework.data.repository.CrudRepository;

import com.wildwestbank.common.db.model.User;

/**
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public interface UsersRepository extends CrudRepository<User, Integer> {

	User findByName(String name);
}
