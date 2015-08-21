/**
 * 
 */
package com.wildwestbank.modules.app.gwt.client.rpc;

import java.util.ArrayList;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.wildwestbank.modules.common.db.model.Person;

/**
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
@RemoteServiceRelativePath("Clients.rpc")
public interface ClientsRpc extends RemoteService {

	static final ClientsRpcAsync Async = (ClientsRpcAsync) (GWT.isClient() ? GWT
			.create(ClientsRpc.class) : null);

	ArrayList<Person> getAll();

	Person save(Person person);

	void remove(Person person);
}
