/**
 * 
 */
package com.wildwestbank.modules.app.gwt.client.views.accounts;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.TriggerClickEvent;
import com.sencha.gxt.widget.core.client.event.TriggerClickEvent.TriggerClickHandler;
import com.sencha.gxt.widget.core.client.form.BigDecimalSpinnerField;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.IntegerField;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.wildwestbank.modules.app.gwt.client.views.BeanEditor;
import com.wildwestbank.modules.app.internal.TransactionTypes;
import com.wildwestbank.modules.common.db.model.Account;
import com.wildwestbank.modules.common.db.model.Transaction;

/**
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public class AccountsOperationsWidget implements BeanEditor<Transaction> {

	public interface Driver extends SimpleBeanEditorDriver<Transaction, AccountsOperationsWidget> {
	}

	private Driver driver = GWT.<Driver> create(Driver.class);

	private static final int LABEL_WIDTH = 150;

	IntegerField transactionType = new IntegerField();
	BigDecimalSpinnerField summ = new BigDecimalSpinnerField();
	TextField destinationAccountNumber = new TextField();
	TextArea message = new TextArea();

	public AccountsOperationsWidget() {
		super();
	}

	private ComboBox<TransactionTypes> createOperationsCombobox() {
		ListStore<TransactionTypes> store = new ListStore<>(
				new ModelKeyProvider<TransactionTypes>() {

					@Override
					public String getKey(TransactionTypes item) {
						return item != null ? String.valueOf(item.getValue()) : null;
					}
				});
		for (TransactionTypes tt : TransactionTypes.values()) {
			store.add(tt);
		}
		final ComboBox<TransactionTypes> operations = new ComboBox<>(store,
				new LabelProvider<TransactionTypes>() {

					@Override
					public String getLabel(TransactionTypes item) {
						return item.getDescription();
					}
				});
		operations.addTriggerClickHandler(new TriggerClickHandler() {

			@Override
			public void onTriggerClick(TriggerClickEvent event) {
				operations.setText("");
			}
		});
		return operations;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.ui.IsWidget#asWidget()
	 */
	@Override
	public Widget asWidget() {
		final VerticalLayoutContainer verticalLayoutContainer = new VerticalLayoutContainer();

		ComboBox<TransactionTypes> operationsCombobox = createOperationsCombobox();
		final FieldLabel operations = new FieldLabel(operationsCombobox, "Операция");
		final FieldLabel summField = new FieldLabel(summ, "Сумма");
		AccountSelectField accountSelect = new AccountSelectField();
		accountSelect.addValueChangeHandler(new ValueChangeHandler<Account>() {

			@Override
			public void onValueChange(ValueChangeEvent<Account> event) {
				if (event.getValue() != null) {
					destinationAccountNumber.setValue(event.getValue().getAccountNumber());
				} else {
					destinationAccountNumber.setValue(null);
				}
			}
		});
		final FieldLabel accountSelectField = new FieldLabel(accountSelect, "Счет назначения");
		final FieldLabel commentField = new FieldLabel(message, "Комментарий");

		operations.setLabelWidth(LABEL_WIDTH);
		summField.setLabelWidth(LABEL_WIDTH);
		accountSelectField.setLabelWidth(LABEL_WIDTH);
		accountSelectField.setVisible(false);
		commentField.setLabelWidth(LABEL_WIDTH);

		operationsCombobox.addSelectionHandler(new SelectionHandler<TransactionTypes>() {

			@Override
			public void onSelection(SelectionEvent<TransactionTypes> event) {
				if (event.getSelectedItem() != null) {
					transactionType.setValue(event.getSelectedItem().getValue());
				} else {
					transactionType.setValue(null);
				}
				if (TransactionTypes.TRANSFER == event.getSelectedItem()) {
					accountSelectField.setVisible(true);
				} else {
					accountSelectField.setVisible(false);
				}

			}
		});

		VerticalLayoutData layoutData = new VerticalLayoutData(1, -1);
		verticalLayoutContainer.add(operations, layoutData);
		verticalLayoutContainer.add(summField, layoutData);
		verticalLayoutContainer.add(accountSelectField);
		verticalLayoutContainer.add(commentField, layoutData);
		return verticalLayoutContainer;
	}

	@Override
	public void init(Transaction bean) {
		driver.initialize(this);
		driver.edit(bean);
	}

	@Override
	public Transaction flush() {
		return driver.flush();
	}

}
