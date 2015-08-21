/**
 * 
 */
package com.wildwestbank.modules.common.security.util;

import org.springframework.util.DigestUtils;

/**
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public class SecurityUtils {

	public static String getPasswordHash(String plainPassword) {
		return DigestUtils.md5DigestAsHex(plainPassword.getBytes());
	}
}
