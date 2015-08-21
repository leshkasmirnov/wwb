/**
 * 
 */
package com.wildwestbank.modules.app.gwt.server;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gwt.thirdparty.guava.common.base.Strings;
import com.wildwestbank.modules.app.gwt.client.rpc.UsersRpc;
import com.wildwestbank.modules.common.db.dao.UsersRepository;
import com.wildwestbank.modules.common.db.model.User;
import com.wildwestbank.modules.common.security.util.SecurityUtils;

/**
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
@Service
public class UsersRpcImpl implements UsersRpc {

	@Autowired
	private UsersRepository usersRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wildwestbank.modules.app.gwt.client.rpc.UsersRpc#getAll()
	 */
	@Override
	public ArrayList<User> getAll() {
		return (ArrayList<User>) usersRepository.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wildwestbank.modules.app.gwt.client.rpc.UsersRpc#removeUser(com.
	 * wildwestbank.modules.common.db.model.User)
	 */
	@Override
	@Transactional
	public void removeUser(User user) {
		usersRepository.delete(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wildwestbank.modules.app.gwt.client.rpc.UsersRpc#saveUser(com.
	 * wildwestbank.modules.common.db.model.User)
	 */
	@Override
	@Transactional
	public User saveUser(User user) {
		if (!Strings.isNullOrEmpty(user.getPlainPassword())) {
			user.setPassword(SecurityUtils.getPasswordHash(user.getPlainPassword()));
		}
		return usersRepository.save(user);
	}

}
