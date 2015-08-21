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
import com.wildwestbank.modules.app.gwt.client.views.clients.ClientsView;
import com.wildwestbank.modules.common.db.model.Person;

/**
 * Поле выбора физ. лица.
 * 
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public class PersonSelectField extends TriggerField<Person> implements TriggerClickHandler {

	private class PersonPropertyEditor extends PropertyEditor<Person> {

		@Override
		public Person parse(CharSequence text) throws ParseException {
			return null;
		}

		@Override
		public String render(Person object) {
			return object.getFio();
		}

	}

	public PersonSelectField() {
		super(new TriggerFieldCell<Person>());
		setPropertyEditor(new PersonPropertyEditor());
		setEditable(false);
		addTriggerClickHandler(this);
	}

	@Override
	public void onTriggerClick(TriggerClickEvent event) {
		final Dialog dialog = new Dialog();
		final ClientsView clientsView = new ClientsView(false) {

			@Override
			protected CustomButtonBar createToolBar() {
				CustomButtonBar createToolBar = super.createToolBar();
				createToolBar.setEnabled(false);
				return createToolBar;
			}

		};

		if (getValue() != null) {
			clientsView.setSelected(getValue());
		}

		dialog.add(clientsView);
		dialog.getButton(PredefinedButton.OK).addSelectHandler(new SelectEvent.SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				PersonSelectField.this.setValue(clientsView.getSelectedValue(), true);
				dialog.hide();
			}
		});
		dialog.setWidth("50%");
		dialog.show();
	}

}
