/**
 * 
 */
package com.wildwestbank.modules.app.gwt.client.views;

import java.util.Arrays;
import java.util.Collection;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.event.StoreAddEvent.StoreAddHandler;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.box.ConfirmMessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent.DialogHideHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.wildwestbank.modules.app.gwt.client.views.clients.ClientsView.ClientsMessages;
import com.wildwestbank.modules.common.db.model.Identified;

/**
 * Базовый класс представления.
 * 
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public abstract class BaseView<M extends Identified> implements IsWidget {

	protected VerticalLayoutContainer verticalLayoutContainer;
	protected CustomButtonBar toolBar;
	protected Grid<M> grid;

	public BaseView() {
	}

	protected CustomButtonBar createToolBar() {
		CustomButtonBar toolBar = new CustomButtonBar();

		TextButton createButton = new TextButton(ClientsMessages.MESSSAGES.createButton());
		TextButton editButton = new TextButton(ClientsMessages.MESSSAGES.editButton());
		TextButton deleteButton = new TextButton(ClientsMessages.MESSSAGES.deleteButton());

		createButton.addSelectHandler(new SelectEvent.SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				handleEditAction(createNewInstance());
			}
		});
		editButton.addSelectHandler(new SelectEvent.SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				handleEditAction(grid.getSelectionModel().getSelectedItem());
			}
		});

		deleteButton.addSelectHandler(new SelectEvent.SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				handleDeleteAction();
			}
		});

		toolBar.addButton(CustomButtonBar.PredefinedButtons.ADD, createButton);
		toolBar.addButton(CustomButtonBar.PredefinedButtons.EDIT, editButton);
		toolBar.addButton(CustomButtonBar.PredefinedButtons.DELETE, deleteButton);
		return toolBar;
	}

	private void handleDeleteAction() {
		final M selectedItem = grid.getSelectionModel().getSelectedItem();
		if (selectedItem != null) {
			ConfirmMessageBox confirmMessageBox = new ConfirmMessageBox("Удаление записи",
					"Вы действительно хотите удалить запись?");

			confirmMessageBox.addDialogHideHandler(new DialogHideHandler() {
				@Override
				public void onDialogHide(DialogHideEvent event) {
					switch (event.getHideButton()) {
					case YES:
						removeRecord(selectedItem);
						break;
					default:
						// error, button added with no specific action ready
					}
				}
			});
			confirmMessageBox.show();
		}

	}

	private void handleEditAction(M selectedItem) {
		final BeanEditor<M> beanEditor = getBeanEditor();
		beanEditor.init(selectedItem);

		final Dialog dialog = new Dialog();
		dialog.add(beanEditor);
		dialog.getButton(PredefinedButton.OK).addSelectHandler(new SelectEvent.SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				dialog.hide();
				afterFlush(beanEditor.flush());
			}
		});
		dialog.setWidth("40%");
		dialog.show();
	}

	protected Grid<M> createGrid() {
		ColumnModel<M> cm = getColumnModel();
		ListStore<M> store = new ListStore<>(new ModelKeyProvider<M>() {

			@Override
			public String getKey(M item) {
				return item != null ? String.valueOf(item.getId()) : null;
			}
		});
		Grid<M> grid = new Grid<>(store, cm);
		grid.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		fillStore(store);
		return grid;
	}

	/**
	 * Override to fill store in subclasses
	 * 
	 * @param store
	 */
	protected void fillStore(ListStore<M> store) {
	}

	protected void updateModel(M model) {
		M finded = grid.getStore().findModel(model);
		if (finded == null) {
			grid.getStore().add(model);
		} else {
			grid.getStore().update(model);
		}
	}

	protected void updateModelOnlyIfExist(M model) {
		M finded = grid.getStore().findModel(model);
		if (finded != null) {
			grid.getStore().update(model);
		}
	}

	protected void removeModel(M model) {
		grid.getStore().remove(model);
	}

	public void refreshStoreWithRecords(Collection<M> items) {
		grid.getStore().clear();
		grid.getStore().addAll(items);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.ui.IsWidget#asWidget()
	 */
	@Override
	public Widget asWidget() {
		if (verticalLayoutContainer == null) {
			verticalLayoutContainer = new VerticalLayoutContainer();
			toolBar = createToolBar();
			grid = createGrid();
			verticalLayoutContainer.add(toolBar, new VerticalLayoutData(1, -1));
			verticalLayoutContainer.add(grid);
		}
		return verticalLayoutContainer;
	}

	public M getSelectedValue() {
		return grid.getSelectionModel().getSelectedItem();
	}

	public void setSelectedValue(M model) {
		M finded = grid.getStore().findModelWithKey(String.valueOf(model.getId()));
		if (finded != null) {
			grid.getSelectionModel().setSelection(Arrays.asList(finded));

		}
	}

	public void addStoreAddHandler(StoreAddHandler<M> handler) {
		grid.getStore().addStoreAddHandler(handler);
	}

	public abstract String getHeaderText();

	protected abstract ColumnModel<M> getColumnModel();

	protected abstract BeanEditor<M> getBeanEditor();

	protected abstract M createNewInstance();

	protected abstract void afterFlush(final M bean);

	protected abstract void removeRecord(final M record);

}
