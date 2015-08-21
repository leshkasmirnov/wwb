/**
 * 
 */
package com.wildwestbank.modules.app.gwt.client.views.users;

import com.google.gwt.core.shared.GWT;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.wildwestbank.modules.common.db.model.User;

/**
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public interface UserProperties extends PropertyAccess<User> {

	static final UserProperties Props = GWT.create(UserProperties.class);

	ValueProvider<User, Integer> id();

	ValueProvider<User, String> name();

	ValueProvider<User, Boolean> active();

	ValueProvider<User, Boolean> superUser();

	ValueProvider<User, String> fio();

	ValueProvider<User, String> tabNumber();
}
