/**
 * 
 */
package com.wildwestbank.common.services;

import com.wildwestbank.gwt.common.shared.AuthenticationResult;
import com.wildwestbank.gwt.common.shared.AuthenticationToken;

/**
 * Сервис безопасности.
 * 
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public interface SecurityService {

	/**
	 * Аутентифицировать.
	 * 
	 * @param token
	 * @return
	 */
	AuthenticationResult authenticate(AuthenticationToken token);

	/**
	 * Выйти.
	 * 
	 * @return
	 */
	AuthenticationResult logOut();
}
