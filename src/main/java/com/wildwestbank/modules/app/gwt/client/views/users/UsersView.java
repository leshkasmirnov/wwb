/**
 * 
 */
package com.wildwestbank.modules.app.gwt.client.views.users;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.cell.core.client.form.CheckBoxCell;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.wildwestbank.modules.app.gwt.client.rpc.UsersRpc;
import com.wildwestbank.modules.app.gwt.client.views.BaseView;
import com.wildwestbank.modules.app.gwt.client.views.BeanEditor;
import com.wildwestbank.modules.common.db.model.User;

/**
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public class UsersView extends BaseView<User> {

	@Override
	public String getHeaderText() {
		return "Пользователи";
	}

	@Override
	protected ColumnModel<User> getColumnModel() {
		ColumnConfig<User, Integer> idColumn = new ColumnConfig<>(UserProperties.Props.id(), 50,
				"ID");
		ColumnConfig<User, String> fioColumn = new ColumnConfig<>(UserProperties.Props.fio(), 200,
				"Фамилия Имя Отчество");
		ColumnConfig<User, String> nameColumn = new ColumnConfig<>(UserProperties.Props.name(),
				100, "Логин");
		ColumnConfig<User, String> tabNumberColumn = new ColumnConfig<>(
				UserProperties.Props.tabNumber(), 150, "Табельный номер");
		ColumnConfig<User, Boolean> activeColumn = new ColumnConfig<>(
				UserProperties.Props.active(), 100, "Активен");
		ColumnConfig<User, Boolean> superUserColumn = new ColumnConfig<>(
				UserProperties.Props.superUser(), 150, "Супер пользователь");

		CheckBoxCell activeCheckBox = new CheckBoxCell();
		CheckBoxCell superUserCheckBox = new CheckBoxCell();

		activeCheckBox.setReadOnly(true);
		superUserCheckBox.setReadOnly(true);

		activeColumn.setCell(activeCheckBox);
		superUserColumn.setCell(superUserCheckBox);

		List<ColumnConfig<User, ?>> list = new ArrayList<>(6);
		list.add(idColumn);
		list.add(fioColumn);
		list.add(nameColumn);
		list.add(tabNumberColumn);
		list.add(activeColumn);
		list.add(superUserColumn);

		return new ColumnModel<>(list);
	}

	@Override
	protected BeanEditor<User> getBeanEditor() {
		return new UserEditor();
	}

	@Override
	protected User createNewInstance() {
		return new User();
	}

	@Override
	protected void afterFlush(User bean) {
		UsersRpc.Async.saveUser(bean, new AsyncCallback<User>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(User result) {
				updateModel(result);
			}
		});
	}

	@Override
	protected void fillStore(final ListStore<User> store) {
		UsersRpc.Async.getAll(new AsyncCallback<ArrayList<User>>() {

			@Override
			public void onSuccess(ArrayList<User> result) {
				store.addAll(result);
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		});

	}

	@Override
	protected void removeRecord(final User record) {
		UsersRpc.Async.removeUser(record, new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(Void result) {
				removeModel(record);
			}
		});
	}

}
