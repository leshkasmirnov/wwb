/**
 * 
 */
package com.wildwestbank.common;

import org.springframework.beans.BeansException;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.RpcTokenException;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RPC;
import com.google.gwt.user.server.rpc.RPCRequest;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.gwt.user.server.rpc.impl.ServerSerializationStreamReader;

/**
 * Диспетчер удаленных запросов делегирующий выполнение бинам спринга.
 * 
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public class SpringRpcServicesDispatcher extends RemoteServiceServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * Reads the payload looking for the interface class name.
	 * 
	 * @param payload
	 * @return
	 * @throws SerializationException
	 */
	private String getInterfaceClassName(String payload) throws SerializationException {
		ServerSerializationStreamReader streamReader = new ServerSerializationStreamReader(Thread
				.currentThread().getContextClassLoader(), this);
		streamReader.prepareToRead(payload);
		return streamReader.readString();
	}

	@Override
	public String processCall(String payload) throws SerializationException {
		// First, check for possible XSRF situation
		checkPermutationStrongName();
		String inrerfaceName = getInterfaceClassName(payload);

		RPCRequest rpcRequest;
		try {
			rpcRequest = RPC.decodeRequest(payload, null, this);
		} catch (IncompatibleRemoteServiceException ex) {
			log("An IncompatibleRemoteServiceException was thrown while processing this call.", ex);
			return RPC.encodeResponseForFailedRequest(null, ex);
		}
		return processCall(inrerfaceName, rpcRequest);
	}

	private String processCall(String inrerfaceName, RPCRequest rpcRequest)
			throws SerializationException {
		RemoteServiceRequestResponseHolder.set(getThreadLocalRequest(), getThreadLocalResponse());
		Object bean = null;
		try {
			try {
				Class<?> interfaceClass = Class.forName(inrerfaceName);
				bean = WebApplicationContextUtils.findWebApplicationContext(getServletContext())
						.getBean(interfaceClass);
			} catch (BeansException e) {
				log("No beans implemented interface " + inrerfaceName
						+ " founded in spring context.", e);
				return RPC.encodeResponseForFailedRequest(rpcRequest, e);
			} catch (ClassNotFoundException e) {
			}

			try {
				onAfterRequestDeserialized(rpcRequest);
				return RPC.invokeAndEncodeResponse(bean, rpcRequest.getMethod(),
						rpcRequest.getParameters(), rpcRequest.getSerializationPolicy(),
						rpcRequest.getFlags());
			} catch (IncompatibleRemoteServiceException ex) {
				log("An IncompatibleRemoteServiceException was thrown while processing this call.",
						ex);
				return RPC.encodeResponseForFailedRequest(rpcRequest, ex);
			} catch (RpcTokenException tokenException) {
				log("An RpcTokenException was thrown while processing this call.", tokenException);
				return RPC.encodeResponseForFailedRequest(rpcRequest, tokenException);
			}
		} finally {
			RemoteServiceRequestResponseHolder.reset();
		}
	}

}
