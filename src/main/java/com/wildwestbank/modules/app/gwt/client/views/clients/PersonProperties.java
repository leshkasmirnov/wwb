/**
 * 
 */
package com.wildwestbank.modules.app.gwt.client.views.clients;

import java.util.Date;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.wildwestbank.modules.common.db.model.Person;

/**
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public interface PersonProperties extends PropertyAccess<Person> {

	public static final PersonProperties props = GWT.create(PersonProperties.class);

	@Path("id")
	ModelKeyProvider<Person> key();

	ValueProvider<Person, Integer> id();

	ValueProvider<Person, String> fio();

	ValueProvider<Person, String> addressAsText();

	ValueProvider<Person, Date> birthday();

	ValueProvider<Person, String> inn();

	ValueProvider<Person, String> email();

	ValueProvider<Person, String> phone();
}
