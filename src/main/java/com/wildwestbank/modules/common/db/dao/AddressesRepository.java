/**
 * 
 */
package com.wildwestbank.modules.common.db.dao;

import org.springframework.data.repository.CrudRepository;

import com.wildwestbank.modules.common.db.model.Address;

/**
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public interface AddressesRepository extends CrudRepository<Address, Integer> {

}
