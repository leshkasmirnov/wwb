/**
 * 
 */
package com.wildwestbank.gwt.common.client.rpc;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.wildwestbank.gwt.common.shared.AuthenticationResult;
import com.wildwestbank.gwt.common.shared.AuthenticationToken;

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
}
