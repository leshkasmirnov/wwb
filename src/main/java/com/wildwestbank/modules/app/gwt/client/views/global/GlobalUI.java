/**
 * 
 */
package com.wildwestbank.modules.app.gwt.client.views.global;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.wildwestbank.modules.app.gwt.client.views.BaseView;
import com.wildwestbank.modules.app.gwt.client.views.accounts.AccountsView;
import com.wildwestbank.modules.app.gwt.client.views.clients.ClientsView;
import com.wildwestbank.modules.app.gwt.client.views.global.MenuPanel.MenuItemClickHandler;
import com.wildwestbank.modules.app.gwt.client.views.transactions.TransactionsView;
import com.wildwestbank.modules.app.gwt.client.views.users.UsersView;
import com.wildwestbank.modules.common.db.model.Identified;
import com.wildwestbank.modules.common.security.UserContext;

/**
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public class GlobalUI implements IsWidget, MenuItemClickHandler {

	private ViewPanel centerPanel;
	private Map<String, BaseView<? extends Identified>> views = new HashMap<>(3);
	private UserContext userContext;

	public GlobalUI(UserContext userContext) {
		this.userContext = userContext;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.ui.IsWidget#asWidget()
	 */
	@Override
	public Widget asWidget() {
		BorderLayoutContainer borderLayoutContainer = new BorderLayoutContainer();

		ContentPanel topPanel = new TopPanel(userContext);
		MenuPanel leftPanel = new MenuPanel(this, userContext);
		centerPanel = new ViewPanel();

		borderLayoutContainer.setBorders(true);

		BorderLayoutData northData = new BorderLayoutData(100);
		northData.setMargins(new Margins(5));

		BorderLayoutData westData = new BorderLayoutData(200);
		westData.setCollapsible(true);
		westData.setSplit(true);
		westData.setCollapseMini(true);
		westData.setMargins(new Margins(0, 5, 0, 5));

		borderLayoutContainer.setNorthWidget(topPanel, northData);
		borderLayoutContainer.setWestWidget(leftPanel, westData);
		borderLayoutContainer.setCenterWidget(centerPanel);

		SimpleContainer container = new SimpleContainer();
		container.add(borderLayoutContainer, new MarginData(0, 20, 20, 0));
		return container;
	}

	@Override
	public void menuItemClick(String buttonId) {
		if (buttonId != null) {
			BaseView<? extends Identified> view;
			// if (views.containsKey(buttonId)) {
			// view = views.get(buttonId);
			// } else {
			switch (buttonId) {
			case MenuPanel.CLIENTS_ID:
				view = new ClientsView();
				break;
			case MenuPanel.ACCOUNTS_ID:
				view = new AccountsView(AccountsView.Mode.ALL);
				break;
			case MenuPanel.TRANSACTIONS_ID:
				view = new TransactionsView();
				break;
			case MenuPanel.USERS_ID:
				view = new UsersView();
				break;
			default:
				throw new IllegalArgumentException(buttonId + " id not handled");
			}
			// views.put(buttonId, view);
			//
			// }
			centerPanel.setView(view);
		}
	}

}
