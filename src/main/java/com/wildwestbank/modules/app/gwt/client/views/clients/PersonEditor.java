/**
 * 
 */
package com.wildwestbank.modules.app.gwt.client.views.clients;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FieldSet;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.wildwestbank.modules.app.gwt.client.views.BeanEditor;
import com.wildwestbank.modules.common.db.model.Person;

/**
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public class PersonEditor implements BeanEditor<Person> {

	public interface Driver extends SimpleBeanEditorDriver<Person, PersonEditor> {
	}

	private Driver driver = GWT.<Driver> create(Driver.class);

	TextField firstName = new TextField();
	TextField lastName = new TextField();
	TextField middleName = new TextField();
	DateField birthday = new DateField();
	TextField inn = new TextField();
	TextField email = new TextField();
	TextField phone = new TextField();
	AddressEditor address = new AddressEditor();

	@Override
	public void init(Person bean) {
		driver.initialize(this);
		driver.edit(bean);
	}

	@Override
	public Widget asWidget() {
		VerticalLayoutContainer layoutContainer = new VerticalLayoutContainer();

		VerticalLayoutData layoutData = new VerticalLayoutData(1, -1);
		layoutContainer.add(new FieldLabel(firstName, "Фамилия"), layoutData);
		layoutContainer.add(new FieldLabel(lastName, "Имя"), layoutData);
		layoutContainer.add(new FieldLabel(middleName, "Отчество"), layoutData);
		layoutContainer.add(new FieldLabel(birthday, "Дата рождения"), layoutData);
		layoutContainer.add(new FieldLabel(inn, "ИНН"), layoutData);
		layoutContainer.add(new FieldLabel(email, "E-mail"), layoutData);
		layoutContainer.add(new FieldLabel(phone, "Телефон"), layoutData);

		FieldSet addressFieldSet = new FieldSet();
		addressFieldSet.setHeadingText("Адрес");
		addressFieldSet.add(address);

		layoutContainer.add(addressFieldSet, layoutData);

		return layoutContainer;
	}

	@Override
	public Person flush() {
		return driver.flush();
	}

}
