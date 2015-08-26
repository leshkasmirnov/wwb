/**
 * 
 */
package com.wildwestbank.modules.app.gwt.client.views.clients;

import java.util.Date;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.i18n.client.Messages;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FieldSet;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.wildwestbank.modules.app.gwt.client.views.BeanEditor;
import com.wildwestbank.modules.app.gwt.client.views.TextFieldNotEmptyValidator;
import com.wildwestbank.modules.app.gwt.client.views.accounts.GenericNotNullValidator;
import com.wildwestbank.modules.common.db.model.Person;

/**
 * Редактор сущности Person.
 * 
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public class PersonEditor implements BeanEditor<Person> {

	public interface PersonEditorMessages extends Messages {

		static final PersonEditorMessages MESSSAGES = GWT.create(PersonEditorMessages.class);

		@DefaultMessage("Фамилия")
		String firstName();

		@DefaultMessage("Имя")
		String lastName();

		@DefaultMessage("Отчество")
		String middleName();

		@DefaultMessage("Дата рождения")
		String birthday();

		@DefaultMessage("ИНН")
		String inn();

		@DefaultMessage("E-mail")
		String email();

		@DefaultMessage("Телефон")
		String phone();

	}

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

		firstName.addValidator(new TextFieldNotEmptyValidator());
		lastName.addValidator(new TextFieldNotEmptyValidator());
		middleName.addValidator(new TextFieldNotEmptyValidator());
		birthday.addValidator(new GenericNotNullValidator<Date>());
		inn.addValidator(new TextFieldNotEmptyValidator());
		email.addValidator(new TextFieldNotEmptyValidator());
		phone.addValidator(new TextFieldNotEmptyValidator());

		VerticalLayoutData layoutData = new VerticalLayoutData(1, -1);
		layoutContainer.add(new FieldLabel(firstName, PersonEditorMessages.MESSSAGES.firstName()),
				layoutData);
		layoutContainer.add(new FieldLabel(lastName, PersonEditorMessages.MESSSAGES.lastName()),
				layoutData);
		layoutContainer
				.add(new FieldLabel(middleName, PersonEditorMessages.MESSSAGES.middleName()),
						layoutData);
		layoutContainer.add(new FieldLabel(birthday, PersonEditorMessages.MESSSAGES.birthday()),
				layoutData);
		layoutContainer.add(new FieldLabel(inn, PersonEditorMessages.MESSSAGES.inn()), layoutData);
		layoutContainer.add(new FieldLabel(email, PersonEditorMessages.MESSSAGES.email()),
				layoutData);
		layoutContainer.add(new FieldLabel(phone, PersonEditorMessages.MESSSAGES.phone()),
				layoutData);

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

	@Override
	public boolean validate() {
		return firstName.validate() && lastName.validate() && middleName.validate()
				&& birthday.validate() && inn.validate() && email.validate() && phone.validate()
				&& address.validate();
	}

}
