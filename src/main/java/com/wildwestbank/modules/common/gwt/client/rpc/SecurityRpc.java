/**
 * 
 */
package com.wildwestbank.modules.common.gwt.client.rpc;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.wildwestbank.modules.common.security.UserContext;
import com.wildwestbank.modules.common.security.auth.AuthenticationResult;
import com.wildwestbank.modules.common.security.auth.AuthenticationToken;

/**
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
@RemoteServiceRelativePath("Security.rpc")
public interface SecurityRpc extends RemoteService {

	static SecurityRpcAsync Async = (SecurityRpcAsync) (GWT.isClient() ? GWT
			.create(SecurityRpc.class) : null);

	AuthenticationResult authenticate(AuthenticationToken authenticationToken);

	AuthenticationResult logOut();

	UserContext getUserContext();
}
