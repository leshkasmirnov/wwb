/**
 * 
 */
package com.wildwestbank.modules.app.gwt.client.views.accounts;

import java.text.ParseException;

import com.sencha.gxt.cell.core.client.form.TriggerFieldCell;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.TriggerClickEvent;
import com.sencha.gxt.widget.core.client.event.TriggerClickEvent.TriggerClickHandler;
import com.sencha.gxt.widget.core.client.form.PropertyEditor;
import com.sencha.gxt.widget.core.client.form.TriggerField;
import com.wildwestbank.modules.app.gwt.client.views.CustomButtonBar;
import com.wildwestbank.modules.app.gwt.client.views.accounts.AccountsView.Mode;
import com.wildwestbank.modules.common.db.model.Account;

/**
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public class AccountSelectField extends TriggerField<Account> implements TriggerClickHandler {

	private class AccountPropertyEditor extends PropertyEditor<Account> {

		@Override
		public Account parse(CharSequence text) throws ParseException {
			return null;
		}

		@Override
		public String render(Account object) {
			return object.getAccountNumber();
		}

	}

	protected AccountSelectField() {
		super(new TriggerFieldCell<Account>());
		setPropertyEditor(new AccountPropertyEditor());
		setEditable(false);
		addTriggerClickHandler(this);
	}

	@Override
	public void onTriggerClick(TriggerClickEvent event) {
		final Dialog dialog = new Dialog();
		final AccountsView accountsView = new AccountsView(Mode.ALL) {

			@Override
			protected CustomButtonBar createToolBar() {
				CustomButtonBar toolBar = super.createToolBar();
				toolBar.setEnabled(false);
				return toolBar;
			}

		};

		if (getValue() != null) {
			accountsView.setSelected(getValue());
		}

		dialog.add(accountsView);
		dialog.getButton(PredefinedButton.OK).addSelectHandler(new SelectEvent.SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				setValue(accountsView.getSelectedValue(), true);
				dialog.hide();
			}
		});
		dialog.setWidth("50%");
		dialog.show();
	}

}
