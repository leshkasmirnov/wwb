/**
 * 
 */
package com.wildwestbank.modules.app.gwt.server;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wildwestbank.modules.app.gwt.client.rpc.ClientsRpc;
import com.wildwestbank.modules.app.services.ClientsService;
import com.wildwestbank.modules.common.db.dao.PersonsRepository;
import com.wildwestbank.modules.common.db.model.Person;

/**
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
@Service
public class ClientsRpcImpl implements ClientsRpc {

	@Autowired
	private PersonsRepository personsRepository;

	@Autowired
	private ClientsService clientsService;

	@Override
	public ArrayList<Person> getAll() {
		return (ArrayList<Person>) personsRepository.findAll();
	}

	@Override
	public Person save(Person person) {
		return clientsService.savePerson(person);
	}

	@Override
	public void remove(Person person) {
		clientsService.removePerson(person);
	}

}
