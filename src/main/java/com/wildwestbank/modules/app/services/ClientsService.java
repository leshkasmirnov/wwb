/**
 * 
 */
package com.wildwestbank.modules.app.services;

import com.wildwestbank.modules.common.db.model.Person;

/**
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public interface ClientsService {

	Person savePerson(Person person);

	void removePerson(Person person);
}
