/**
 * 
 */
package com.wildwestbank.modules.app.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wildwestbank.modules.app.services.ClientsService;
import com.wildwestbank.modules.common.db.dao.AddressesRepository;
import com.wildwestbank.modules.common.db.dao.PersonsRepository;
import com.wildwestbank.modules.common.db.model.Address;
import com.wildwestbank.modules.common.db.model.Person;

/**
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
@Service
public class ClientsServiceImpl implements ClientsService {

	@Autowired
	private PersonsRepository personsRepository;
	@Autowired
	private AddressesRepository addressesRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wildwestbank.modules.app.services.ClientsService#savePerson(com.
	 * wildwestbank.modules.common.db.model.Person)
	 */
	@Override
	@Transactional
	public Person savePerson(Person person) {
		Address address = addressesRepository.save(person.getAddress());
		person.setAddress(address);
		return personsRepository.save(person);
	}

	@Override
	@Transactional
	public void removePerson(Person person) {
		Address address = person.getAddress();
		personsRepository.delete(person);
		addressesRepository.delete(address);
	}

}
