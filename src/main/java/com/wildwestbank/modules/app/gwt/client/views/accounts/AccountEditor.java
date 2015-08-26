/**
 * 
 */
package com.wildwestbank.modules.app.gwt.client.views.accounts;

import java.util.Date;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.i18n.client.Messages;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.wildwestbank.modules.app.gwt.client.views.BeanEditor;
import com.wildwestbank.modules.app.gwt.client.views.TextFieldNotEmptyValidator;
import com.wildwestbank.modules.app.gwt.client.views.accounts.AccountsView.Mode;
import com.wildwestbank.modules.common.db.model.Account;
import com.wildwestbank.modules.common.db.model.Person;

/**
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public class AccountEditor implements BeanEditor<Account> {

	public interface AccountEditorMessages extends Messages {

		static final AccountEditorMessages MESSSAGES = GWT.create(AccountEditorMessages.class);

		@DefaultMessage("Номер счета")
		String accountNumber();

		@DefaultMessage("Владелец")
		String person();

		@DefaultMessage("Дата открытия")
		String openDate();

		@DefaultMessage("Дата закрытия")
		String closeDate();

	}

	public interface Driver extends SimpleBeanEditorDriver<Account, AccountEditor> {
	}

	private Driver driver = GWT.<Driver> create(Driver.class);

	TextField accountNumber = new TextField();

	PersonSelectField person = new PersonSelectField();
	DateField openDate = new DateField();
	DateField closeDate = new DateField();

	public AccountEditor(Mode mode) {
		if (AccountsView.Mode.SINGLE == mode) {
			person.setEnabled(false);
		}
	}

	@Override
	public Widget asWidget() {
		VerticalLayoutContainer verticalLayoutContainer = new VerticalLayoutContainer();
		VerticalLayoutData layoutData = new VerticalLayoutData(1, -1);

		accountNumber.addValidator(new TextFieldNotEmptyValidator());
		person.addValidator(new GenericNotNullValidator<Person>());
		openDate.addValidator(new GenericNotNullValidator<Date>());

		verticalLayoutContainer.add(
				new FieldLabel(accountNumber, AccountEditorMessages.MESSSAGES.accountNumber()),
				layoutData);
		verticalLayoutContainer.add(
				new FieldLabel(person, AccountEditorMessages.MESSSAGES.person()), layoutData);
		verticalLayoutContainer.add(
				new FieldLabel(openDate, AccountEditorMessages.MESSSAGES.openDate()), layoutData);
		verticalLayoutContainer.add(
				new FieldLabel(closeDate, AccountEditorMessages.MESSSAGES.closeDate()), layoutData);

		return verticalLayoutContainer;
	}

	@Override
	public void init(Account bean) {
		driver.initialize(this);
		driver.edit(bean);
	}

	@Override
	public Account flush() {
		return driver.flush();
	}

	@Override
	public boolean validate() {
		return accountNumber.validate() && person.validate() && openDate.validate();
	}

}
