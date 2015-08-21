/**
 * 
 */
package com.wildwestbank.modules.app.gwt.client.views.transactions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.state.client.GridFilterStateHandler;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.filters.DateFilter;
import com.sencha.gxt.widget.core.client.grid.filters.GridFilters;
import com.sencha.gxt.widget.core.client.grid.filters.StringFilter;
import com.wildwestbank.modules.app.gwt.client.rpc.TransactionsRpc;
import com.wildwestbank.modules.app.gwt.client.views.BaseView;
import com.wildwestbank.modules.app.gwt.client.views.BeanEditor;
import com.wildwestbank.modules.app.gwt.client.views.CustomButtonBar;
import com.wildwestbank.modules.common.db.model.Transaction;

/**
 * Представление интерфейса "Транзакции".
 * 
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public class TransactionsView extends BaseView<Transaction> {

	@Override
	public String getHeaderText() {
		return "Транзакции";
	}

	@Override
	protected ColumnModel<Transaction> getColumnModel() {
		ColumnConfig<Transaction, Date> dateColumn = new ColumnConfig<>(
				TransactionProperties.Props.transactionDate(), 100, "Дата");
		ColumnConfig<Transaction, String> transactionTypeColumn = new ColumnConfig<>(
				TransactionProperties.Props.transactionTypeAsText(), 100, "Тип транзакции");
		ColumnConfig<Transaction, String> sourceAccountNumberColumn = new ColumnConfig<>(
				TransactionProperties.Props.sourceAccountNumber(), 100, "Счет");
		ColumnConfig<Transaction, String> personFioColumn = new ColumnConfig<>(
				TransactionProperties.Props.personFio(), 150, "Владелец");
		ColumnConfig<Transaction, String> destinationAccountNumberColumn = new ColumnConfig<>(
				TransactionProperties.Props.destinationAccountNumber(), 100, "Счет назначения");
		ColumnConfig<Transaction, BigDecimal> summColumn = new ColumnConfig<>(
				TransactionProperties.Props.summ(), 100, "Сумма");
		ColumnConfig<Transaction, String> messageColumn = new ColumnConfig<>(
				TransactionProperties.Props.message(), 100, "Комментарий");

		final NumberFormat number = NumberFormat.getFormat("0.00");
		summColumn.setCell(new AbstractCell<BigDecimal>() {
			@Override
			public void render(Context context, BigDecimal value, SafeHtmlBuilder sb) {
				String v = number.format(value);
				sb.appendHtmlConstant("<span qtitle='Change' qtip='" + v + "'>" + v + "</span>");
			}
		});
		dateColumn.setCell(new DateCell(DateTimeFormat.getFormat("dd.MM.yyyy HH:mm")));

		List<ColumnConfig<Transaction, ?>> list = new ArrayList<>(7);
		list.add(dateColumn);
		list.add(transactionTypeColumn);
		list.add(sourceAccountNumberColumn);
		list.add(personFioColumn);
		list.add(destinationAccountNumberColumn);
		list.add(summColumn);
		list.add(messageColumn);
		return new ColumnModel<>(list);
	}

	@Override
	protected BeanEditor<Transaction> getBeanEditor() {
		return null;
	}

	@Override
	protected Transaction createNewInstance() {
		return null;
	}

	@Override
	protected void afterFlush(Transaction bean) {
	}

	@Override
	protected void removeRecord(Transaction record) {
	}

	@Override
	protected void fillStore(final ListStore<Transaction> store) {
		TransactionsRpc.Async.getAll(new AsyncCallback<ArrayList<Transaction>>() {

			@Override
			public void onSuccess(ArrayList<Transaction> result) {
				store.addAll(result);
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		});
	}

	@Override
	protected CustomButtonBar createToolBar() {
		return new CustomButtonBar();
	}

	@Override
	protected Grid<Transaction> createGrid() {
		Grid<Transaction> createGrid = super.createGrid();

		DateFilter<Transaction> dateFilter = new DateFilter<Transaction>(
				TransactionProperties.Props.transactionDate());
		StringFilter<Transaction> personFioFilter = new StringFilter<Transaction>(
				TransactionProperties.Props.personFio());

		GridFilters<Transaction> filters = new GridFilters<Transaction>();
		filters.initPlugin(createGrid);
		filters.setLocal(true);
		filters.addFilter(dateFilter);
		filters.addFilter(personFioFilter);

		// Stage manager, load the previous state
		GridFilterStateHandler<Transaction> handler = new GridFilterStateHandler<Transaction>(
				createGrid, filters);
		handler.loadState();
		return createGrid;
	}

}
