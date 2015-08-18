/**
 * 
 */
package com.wildwestbank.gwt.common.client.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.wildwestbank.gwt.common.shared.AuthenticationResult;
import com.wildwestbank.gwt.common.shared.AuthenticationToken;

/**
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public interface SecurityRpcAsync {

	void authenticate(AuthenticationToken authenticationToken,
			AsyncCallback<AuthenticationResult> callback);

	void logOut(AsyncCallback<AuthenticationResult> callback);
}
