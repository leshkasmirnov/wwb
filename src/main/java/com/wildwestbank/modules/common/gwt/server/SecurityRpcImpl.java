/**
 * 
 */
package com.wildwestbank.modules.common.gwt.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wildwestbank.modules.common.gwt.client.rpc.SecurityRpc;
import com.wildwestbank.modules.common.security.SecurityContext;
import com.wildwestbank.modules.common.security.UserContext;
import com.wildwestbank.modules.common.security.auth.AuthenticationResult;
import com.wildwestbank.modules.common.security.auth.AuthenticationToken;
import com.wildwestbank.modules.common.services.SecurityService;
import com.wildwestbank.spring.RemoteServiceRequestResponseHolder;

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

	@Override
	public UserContext getUserContext() {
		SecurityContext sc = (SecurityContext) RemoteServiceRequestResponseHolder
				.getHttpServletRequest().getSession().getAttribute(SecurityContext.class.getName());
		return sc.getUserContext();
	}

}
