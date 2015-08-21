/**
 * 
 */
package com.wildwestbank.modules.common.gwt.client.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.wildwestbank.modules.common.security.UserContext;
import com.wildwestbank.modules.common.security.auth.AuthenticationResult;
import com.wildwestbank.modules.common.security.auth.AuthenticationToken;

/**
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public interface SecurityRpcAsync {

	void authenticate(AuthenticationToken authenticationToken,
			AsyncCallback<AuthenticationResult> callback);

	void logOut(AsyncCallback<AuthenticationResult> callback);

	void getUserContext(AsyncCallback<UserContext> callback);
}
