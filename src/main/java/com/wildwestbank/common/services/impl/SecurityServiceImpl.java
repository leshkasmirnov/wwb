/**
 * 
 */
package com.wildwestbank.common.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.wildwestbank.common.RemoteServiceRequestResponseHolder;
import com.wildwestbank.common.db.dao.UsersRepository;
import com.wildwestbank.common.db.model.User;
import com.wildwestbank.common.security.SecurityContext;
import com.wildwestbank.common.security.UserContext;
import com.wildwestbank.common.services.SecurityService;
import com.wildwestbank.gwt.common.shared.AuthenticationResult;
import com.wildwestbank.gwt.common.shared.AuthenticationResult.AuthenticationState;
import com.wildwestbank.gwt.common.shared.AuthenticationToken;

/**
 * @see SecurityService
 * 
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
@Service
public class SecurityServiceImpl implements SecurityService {

	@Autowired
	private UsersRepository usersRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wildwestbank.common.services.SecurityService#authenticate(com.
	 * wildwestbank.gwt.login.shared.AuthenticationToken)
	 */
	@Override
	public AuthenticationResult authenticate(AuthenticationToken token) {
		AuthenticationResult result = new AuthenticationResult();

		User finded = usersRepository.findByName(token.getUserName());

		if (finded != null) {
			boolean equals = checkPassword(finded, token.getPassword());
			if (equals) {
				UserContext userContext = new UserContext(token.getUserName(), token.getPassword());
				SecurityContext context = new SecurityContext();

				context.setUserContext(userContext);

				RemoteServiceRequestResponseHolder.getHttpServletRequest().getSession()
						.setAttribute(SecurityContext.class.getName(), context);

				result.setState(AuthenticationState.OK);
			} else {
				result.setMessage("Пароли не совпадают!");
				result.setState(AuthenticationState.ERROR);
			}
		} else {
			result.setMessage("Пользователь не найден!");
			result.setState(AuthenticationState.ERROR);
		}

		return result;
	}

	private boolean checkPassword(User finded, String plainPassword) {
		return DigestUtils.md5DigestAsHex(plainPassword.getBytes()).equals(finded.getPassword());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wildwestbank.common.services.SecurityService#logOut()
	 */
	@Override
	public AuthenticationResult logOut() {
		AuthenticationResult result = new AuthenticationResult();

		RemoteServiceRequestResponseHolder.getHttpServletRequest().getSession()
				.setAttribute(SecurityContext.class.getName(), null);

		result.setState(AuthenticationState.OK);
		return result;
	}

}
