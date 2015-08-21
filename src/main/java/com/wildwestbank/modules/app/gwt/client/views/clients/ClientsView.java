/**
 * 
 */
package com.wildwestbank.modules.app.gwt.client.views.clients;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.IsWidget;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.wildwestbank.modules.app.gwt.client.rpc.AccountsRpc;
import com.wildwestbank.modules.app.gwt.client.rpc.ClientsRpc;
import com.wildwestbank.modules.app.gwt.client.views.BaseDetailedView;
import com.wildwestbank.modules.app.gwt.client.views.BeanEditor;
import com.wildwestbank.modules.app.gwt.client.views.accounts.AccountsView;
import com.wildwestbank.modules.common.db.model.Account;
import com.wildwestbank.modules.common.db.model.Address;
import com.wildwestbank.modules.common.db.model.Person;

/**
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public class ClientsView extends BaseDetailedView<Person> {

	public interface ClientsMessages extends Messages {

		static final ClientsMessages MESSSAGES = GWT.create(ClientsMessages.class);

		@DefaultMessage("ID")
		String idColumn();

		@DefaultMessage("Фамилия Имя Отчество")
		String fioColumn();

		@DefaultMessage("Адрес")
		String addressColumn();

		@DefaultMessage("Дата рождения")
		String birthdayColumn();

		@DefaultMessage("Создать запись")
		String createButton();

		@DefaultMessage("Редактировать запись")
		String editButton();

		@DefaultMessage("Удалить запись")
		String deleteButton();
	}

	private AccountsView accountsView;
	private Person selected;

	public ClientsView() {
		super();
	}

	public ClientsView(boolean enableDetalization) {
		super(enableDetalization);
	}

	@Override
	public String getHeaderText() {
		return "Клиенты";
	}

	@Override
	protected ColumnModel<Person> getColumnModel() {
		ColumnConfig<Person, Integer> idColumnConfig = new ColumnConfig<>(
				PersonProperties.props.id(), 50, ClientsMessages.MESSSAGES.idColumn());
		ColumnConfig<Person, String> fioColumnConfig = new ColumnConfig<>(
				PersonProperties.props.fio(), 300, ClientsMessages.MESSSAGES.fioColumn());
		ColumnConfig<Person, String> addressColumnConfig = new ColumnConfig<>(
				PersonProperties.props.addressAsText(), 500,
				ClientsMessages.MESSSAGES.addressColumn());
		ColumnConfig<Person, Date> birthdayColumnConfig = new ColumnConfig<>(
				PersonProperties.props.birthday(), 200, ClientsMessages.MESSSAGES.birthdayColumn());
		birthdayColumnConfig.setCell(new DateCell(DateTimeFormat.getFormat("dd.MM.yyyy")));
		List<ColumnConfig<Person, ?>> list = new ArrayList<>(4);

		list.add(idColumnConfig);
		list.add(fioColumnConfig);
		list.add(addressColumnConfig);
		list.add(birthdayColumnConfig);
		return new ColumnModel<>(list);
	}

	@Override
	protected void fillStore(final ListStore<Person> store) {
		ClientsRpc.Async.getAll(new AsyncCallback<ArrayList<Person>>() {

			@Override
			public void onSuccess(ArrayList<Person> result) {
				store.addAll(result);
				if (getSelected() != null) {
					setSelectedValue(getSelected());
				}
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		});

	}

	@Override
	protected BeanEditor<Person> getBeanEditor() {
		return new PersonEditor();
	}

	@Override
	protected Person createNewInstance() {
		Address address = new Address();
		Person person = new Person();
		person.setAddress(address);
		return person;
	}

	@Override
	protected void afterFlush(final Person bean) {
		ClientsRpc.Async.save(bean, new AsyncCallback<Person>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(Person result) {
				updateModel(result);
			}
		});

	}

	@Override
	protected void removeRecord(final Person record) {
		ClientsRpc.Async.remove(record, new AsyncCallback<Void>() {

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
	protected IsWidget getDetailFor(final Person model) {
		if (accountsView == null) {
			accountsView = new AccountsView(AccountsView.Mode.SINGLE);
		}
		accountsView.setOwner(model);
		AccountsRpc.Async.getForPerson(model, new AsyncCallback<ArrayList<Account>>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(ArrayList<Account> result) {
				accountsView.refreshStoreWithRecords(result);
			}
		});
		return accountsView;
	}

	@Override
	public String getDetailHeaderText() {
		return "Счета";
	}

	public Person getSelected() {
		return selected;
	}

	public void setSelected(Person selected) {
		this.selected = selected;
	}

}
