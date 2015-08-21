/**
 * 
 */
package com.wildwestbank.modules.common.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wildwestbank.modules.common.db.dao.UsersRepository;
import com.wildwestbank.modules.common.db.model.User;
import com.wildwestbank.modules.common.security.SecurityContext;
import com.wildwestbank.modules.common.security.UserContext;
import com.wildwestbank.modules.common.security.auth.AuthenticationResult;
import com.wildwestbank.modules.common.security.auth.AuthenticationResult.AuthenticationState;
import com.wildwestbank.modules.common.security.auth.AuthenticationToken;
import com.wildwestbank.modules.common.security.util.SecurityUtils;
import com.wildwestbank.modules.common.services.SecurityService;
import com.wildwestbank.spring.RemoteServiceRequestResponseHolder;

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
			if (finded.getActive()) {

				boolean equals = checkPassword(finded, token.getPassword());
				if (equals) {
					UserContext userContext = new UserContext(token.getUserName(),
							token.getPassword(), finded.getId(), finded.getSuperUser());
					userContext.setFio(finded.getFio());
					SecurityContext context = new SecurityContext();

					context.setUserContext(userContext);

					RemoteServiceRequestResponseHolder.getHttpServletRequest().getSession()
							.setAttribute(SecurityContext.class.getName(), context);

					result.setState(AuthenticationState.OK);
				} else {
					result.setMessage("Не верный пароль!");
					result.setState(AuthenticationState.ERROR);
				}
			} else {
				result.setMessage("Пользователь не активен!");
				result.setState(AuthenticationState.ERROR);
			}
		} else {
			result.setMessage("Пользователь не найден!");
			result.setState(AuthenticationState.ERROR);
		}

		return result;
	}

	private boolean checkPassword(User finded, String plainPassword) {
		return SecurityUtils.getPasswordHash(plainPassword).equals(finded.getPassword());

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
