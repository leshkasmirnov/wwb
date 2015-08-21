/**
 * 
 */
package com.wildwestbank.modules.app.gwt.client.rpc;

import java.util.ArrayList;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.wildwestbank.modules.common.db.model.User;

/**
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
@RemoteServiceRelativePath("UsersRpc.rpc")
public interface UsersRpc extends RemoteService {

	static final UsersRpcAsync Async = (UsersRpcAsync) (GWT.isClient() ? GWT.create(UsersRpc.class)
			: null);

	ArrayList<User> getAll();

	void removeUser(User user);

	User saveUser(User user);
}
