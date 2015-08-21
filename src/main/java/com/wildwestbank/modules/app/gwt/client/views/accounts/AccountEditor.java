/**
 * 
 */
package com.wildwestbank.modules.app.gwt.client.views.accounts;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.wildwestbank.modules.app.gwt.client.views.BeanEditor;
import com.wildwestbank.modules.app.gwt.client.views.accounts.AccountsView.Mode;
import com.wildwestbank.modules.common.db.model.Account;

/**
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public class AccountEditor implements BeanEditor<Account> {

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

		verticalLayoutContainer.add(new FieldLabel(accountNumber, "Номер счета"), layoutData);
		verticalLayoutContainer.add(new FieldLabel(person, "Владелец"), layoutData);
		verticalLayoutContainer.add(new FieldLabel(openDate, "Дата открытия"), layoutData);
		verticalLayoutContainer.add(new FieldLabel(closeDate, "Дата закрытия"), layoutData);

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

}
