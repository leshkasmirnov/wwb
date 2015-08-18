/**
 * 
 */
package com.wildwestbank.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public class RemoteServiceRequestResponseHolder {

	private static ThreadLocal<HttpServletRequest> servletRequest;
	private static ThreadLocal<HttpServletResponse> servletResponse;

	private static void validate() {
		if (servletRequest == null) {
			servletRequest = new ThreadLocal<>();
		}
		if (servletResponse == null) {
			servletResponse = new ThreadLocal<>();
		}
	}

	public static void set(HttpServletRequest request, HttpServletResponse response) {
		validate();
		servletRequest.set(request);
		servletResponse.set(response);
	}

	public static HttpServletRequest getHttpServletRequest() {
		return servletRequest != null ? servletRequest.get() : null;
	}

	public static HttpServletResponse getHttpServletResponse() {
		return servletResponse != null ? servletResponse.get() : null;
	}

	public static void reset() {
		servletRequest.set(null);
		servletResponse.set(null);
	}

}
