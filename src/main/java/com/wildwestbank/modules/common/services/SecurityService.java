/**
 * 
 */
package com.wildwestbank.modules.common.services;

import com.wildwestbank.modules.common.security.auth.AuthenticationResult;
import com.wildwestbank.modules.common.security.auth.AuthenticationToken;

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
