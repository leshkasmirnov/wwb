/**
 * 
 */
package com.wildwestbank.modules.app.gwt.client.rpc;

import java.util.ArrayList;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.wildwestbank.modules.common.db.model.Transaction;

/**
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
@RemoteServiceRelativePath("TransactionsRpc.rpc")
public interface TransactionsRpc extends RemoteService {

	static final TransactionsRpcAsync Async = (TransactionsRpcAsync) (GWT.isClient() ? GWT
			.create(TransactionsRpc.class) : null);

	ArrayList<Transaction> getAll();
}
