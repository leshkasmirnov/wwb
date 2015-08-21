/**
 * 
 */
package com.wildwestbank.modules.common.db.dao;

import org.springframework.data.repository.CrudRepository;

import com.wildwestbank.modules.common.db.model.Person;

/**
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public interface PersonsRepository extends CrudRepository<Person, Integer> {

}
