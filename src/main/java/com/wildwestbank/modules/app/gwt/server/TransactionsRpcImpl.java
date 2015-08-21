/**
 * 
 */
package com.wildwestbank.modules.app.gwt.server;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wildwestbank.modules.app.gwt.client.rpc.TransactionsRpc;
import com.wildwestbank.modules.common.db.dao.TransactionsRepository;
import com.wildwestbank.modules.common.db.model.Transaction;

/**
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
@Service
public class TransactionsRpcImpl implements TransactionsRpc {

	@Autowired
	private TransactionsRepository transactionsRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wildwestbank.modules.app.gwt.client.rpc.TransactionsRpc#getAll()
	 */
	@Override
	public ArrayList<Transaction> getAll() {
		return (ArrayList<Transaction>) transactionsRepository.getAllWithPersonFio();
	}

}
