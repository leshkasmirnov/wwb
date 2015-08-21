/**
 * 
 */
package com.wildwestbank.modules.app.gwt.client.rpc;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.wildwestbank.modules.common.db.model.User;

/**
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public interface UsersRpcAsync {

	void getAll(AsyncCallback<ArrayList<User>> callback);

	void removeUser(User user, AsyncCallback<Void> callback);

	void saveUser(User user, AsyncCallback<User> callback);
}
