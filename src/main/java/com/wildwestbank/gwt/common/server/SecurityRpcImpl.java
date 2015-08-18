/**
 * 
 */
package com.wildwestbank.gwt.common.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wildwestbank.common.services.SecurityService;
import com.wildwestbank.gwt.common.client.rpc.SecurityRpc;
import com.wildwestbank.gwt.common.shared.AuthenticationResult;
import com.wildwestbank.gwt.common.shared.AuthenticationToken;

/**
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
@Service
public class SecurityRpcImpl implements SecurityRpc {

	@Autowired
	private SecurityService securityService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wildwestbank.gwt.login.client.rpc.SecurityRpc#authenticate(com.
	 * wildwestbank.gwt.login.shared.AuthenticationToken)
	 */
	@Override
	public AuthenticationResult authenticate(AuthenticationToken authenticationToken) {
		return securityService.authenticate(authenticationToken);
	}

	@Override
	public AuthenticationResult logOut() {
		return securityService.logOut();
	}

}
