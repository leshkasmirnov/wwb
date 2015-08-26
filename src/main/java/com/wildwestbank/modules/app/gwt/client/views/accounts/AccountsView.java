/**
 * 
 */
package com.wildwestbank.modules.app.gwt.client.views.accounts;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.state.client.GridFilterStateHandler;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.box.ConfirmMessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent.DialogHideHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.filters.GridFilters;
import com.sencha.gxt.widget.core.client.grid.filters.StringFilter;
import com.wildwestbank.modules.app.gwt.client.rpc.AccountsRpc;
import com.wildwestbank.modules.app.gwt.client.views.BaseView;
import com.wildwestbank.modules.app.gwt.client.views.BeanEditor;
import com.wildwestbank.modules.app.gwt.client.views.CustomButtonBar;
import com.wildwestbank.modules.common.db.model.Account;
import com.wildwestbank.modules.common.db.model.Person;
import com.wildwestbank.modules.common.db.model.Transaction;

/**
 * Представление интерфейса "Счета".
 * 
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public class AccountsView extends BaseView<Account> {

	private static final String DATE_FORMAT = "dd.MM.yyyy HH:mm";

	public enum Mode {
		ALL, SINGLE;
	}

	public interface AccountsMessages extends Messages {

		static final AccountsMessages MESSSAGES = GWT.create(AccountsMessages.class);

		@DefaultMessage("Номер счета")
		String numberColumn();

		@DefaultMessage("Владелец")
		String ownerColumn();

		@DefaultMessage("Дата открытия")
		String openDateColumn();

		@DefaultMessage("Дата закрытия")
		String closeDateColumn();

		@DefaultMessage("Количество денег")
		String amountColumn();

		@DefaultMessage("Счета")
		String headerText();

		@DefaultMessage("Закрыть счет")
		String closeAccountButtonCaption();

		@DefaultMessage("Операции со счетом")
		String accountOperationsButtonCaption();

	}

	private final Mode mode;
	private Person owner;
	private Account selected;

	public AccountsView(Mode mode) {
		this.mode = mode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wildwestbank.gwt.app.client.views.BaseView#getHeaderText()
	 */
	@Override
	public String getHeaderText() {
		return AccountsMessages.MESSSAGES.headerText();
	}

	@Override
	protected ColumnModel<Account> getColumnModel() {
		List<ColumnConfig<Account, ?>> list = new ArrayList<>();

		ColumnConfig<Account, String> accountNumber = new ColumnConfig<>(
				AccountProperties.Props.accountNumber(), 200,
				AccountsMessages.MESSSAGES.numberColumn());
		ColumnConfig<Account, String> owner = null;
		if (Mode.ALL == mode) {
			owner = new ColumnConfig<>(AccountProperties.Props.personAsText(), 200,
					AccountsMessages.MESSSAGES.ownerColumn());
		}
		ColumnConfig<Account, Date> openDate = new ColumnConfig<>(
				AccountProperties.Props.openDate(), 200,
				AccountsMessages.MESSSAGES.openDateColumn());
		ColumnConfig<Account, Date> closeDate = new ColumnConfig<>(
				AccountProperties.Props.closeDate(), 200,
				AccountsMessages.MESSSAGES.closeDateColumn());
		ColumnConfig<Account, BigDecimal> amount = new ColumnConfig<>(
				AccountProperties.Props.amount(), 200, AccountsMessages.MESSSAGES.amountColumn());

		final NumberFormat number = NumberFormat.getFormat("0.00");
		amount.setCell(new AbstractCell<BigDecimal>() {
			@Override
			public void render(Context context, BigDecimal value, SafeHtmlBuilder sb) {
				String v = number.format(value);
				sb.appendHtmlConstant("<span qtitle='Change' qtip='" + v + "'>" + v + "</span>");
			}
		});

		openDate.setCell(new DateCell(DateTimeFormat.getFormat(DATE_FORMAT)));
		closeDate.setCell(new DateCell(DateTimeFormat.getFormat(DATE_FORMAT)));

		list.add(accountNumber);
		if (owner != null) {
			list.add(owner);
		}
		list.add(openDate);
		list.add(closeDate);
		list.add(amount);
		return new ColumnModel<>(list);
	}

	@Override
	protected BeanEditor<Account> getBeanEditor() {
		return new AccountEditor(mode);
	}

	@Override
	protected Account createNewInstance() {
		Account account = new Account();
		account.setAmount(new BigDecimal(0));
		account.setPerson(getOwner());
		return account;
	}

	@Override
	protected void afterFlush(Account bean) {
		AccountsRpc.Async.save(bean, new AsyncCallback<Account>() {

			@Override
			public void onSuccess(Account result) {
				updateModel(result);
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		});
	}

	@Override
	protected void removeRecord(final Account record) {
		AccountsRpc.Async.delete(record, new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(Void result) {
				removeModel(record);
			}
		});
	}

	@Override
	protected void fillStore(final ListStore<Account> store) {
		if (Mode.ALL == mode) {
			AccountsRpc.Async.getAll(new AsyncCallback<ArrayList<Account>>() {

				@Override
				public void onFailure(Throwable caught) {
					System.out.println();
				}

				@Override
				public void onSuccess(ArrayList<Account> result) {
					store.addAll(result);
					if (selected != null) {
						setSelectedValue(selected);
					}
				}
			});
		}
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}

	public Person getOwner() {
		return owner;
	}

	@Override
	protected CustomButtonBar createToolBar() {
		CustomButtonBar toolBar = super.createToolBar();
		toolBar.removeButton(CustomButtonBar.PredefinedButtons.EDIT);
		toolBar.removeButton(CustomButtonBar.PredefinedButtons.DELETE);

		TextButton closeAccountButton = new TextButton(
				AccountsMessages.MESSSAGES.closeAccountButtonCaption());
		TextButton accountOperationsButton = new TextButton(
				AccountsMessages.MESSSAGES.accountOperationsButtonCaption());

		closeAccountButton.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				handleCloseAccountAction();
			}
		});
		accountOperationsButton.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				if (getSelectedValue() != null) {
					showAccountOperationsDialog(getSelectedValue());
				}
			}
		});

		toolBar.addButton("closeAccount", closeAccountButton);
		toolBar.addButton("accountOperations", accountOperationsButton);
		return toolBar;
	}

	private void showAccountOperationsDialog(Account selectedValue) {
		final AccountOperationsDialog dialog = new AccountOperationsDialog(selectedValue);
		dialog.init(new Transaction());
		dialog.getButton(PredefinedButton.OK).addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				if (dialog.validate()) {
					Transaction t = dialog.flush();
					doTransaction(t);
					dialog.hide();
				}
			}
		});
		dialog.show();
	}

	private void doTransaction(Transaction t) {
		AccountsRpc.Async.doTransaction(getSelectedValue(), t,
				new AsyncCallback<ArrayList<Account>>() {

					@Override
					public void onFailure(Throwable caught) {
					}

					@Override
					public void onSuccess(ArrayList<Account> result) {
						for (Account account : result) {
							updateModelOnlyIfExist(account);
						}
					}
				});

	}

	private void handleCloseAccountAction() {
		if (getSelectedValue() != null) {
			final Account selected = getSelectedValue();
			ConfirmMessageBox confirmMessageBox = new ConfirmMessageBox("Закрытие счета",
					"Закрыть счет № " + selected.getAccountNumber() + " ?");
			confirmMessageBox.addDialogHideHandler(new DialogHideHandler() {

				@Override
				public void onDialogHide(DialogHideEvent event) {
					switch (event.getHideButton()) {
					case YES:
						closeAccount(selected);
						break;
					default:
					}

				}
			});
			confirmMessageBox.show();
		}
	}

	private void closeAccount(Account selected) {
		selected.setCloseDate(new Date());
		AccountsRpc.Async.save(selected, new AsyncCallback<Account>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(Account result) {
				updateModel(result);
			}
		});
	}

	public void setSelected(Account value) {
		this.selected = value;
	}

	@Override
	protected Grid<Account> createGrid() {
		Grid<Account> createGrid = super.createGrid();

		StringFilter<Account> accountNumberFilter = new StringFilter<Account>(
				AccountProperties.Props.accountNumber());
		StringFilter<Account> fioFilter = new StringFilter<Account>(
				AccountProperties.Props.personAsText());

		GridFilters<Account> filters = new GridFilters<Account>();
		filters.initPlugin(createGrid);
		filters.setLocal(true);
		filters.addFilter(accountNumberFilter);
		filters.addFilter(fioFilter);

		// Stage manager, load the previous state
		GridFilterStateHandler<Account> handler = new GridFilterStateHandler<Account>(createGrid,
				filters);
		handler.loadState();
		return createGrid;
	}

}
