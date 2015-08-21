/**
 * 
 */
package com.wildwestbank.modules.app.gwt.client.rpc;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.wildwestbank.modules.common.db.model.Person;

/**
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public interface ClientsRpcAsync {

	void getAll(AsyncCallback<ArrayList<Person>> callback);

	void save(Person person, AsyncCallback<Person> callback);

	void remove(Person person, AsyncCallback<Void> callback);
}
