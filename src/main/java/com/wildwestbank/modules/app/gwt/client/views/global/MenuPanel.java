/**
 * 
 */
package com.wildwestbank.modules.app.gwt.client.views.global;

import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.ToggleButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.wildwestbank.modules.common.security.UserContext;

/**
 * Левая панель меню.
 * 
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public class MenuPanel extends ContentPanel implements SelectHandler {

	public interface MenuItemClickHandler {
		void menuItemClick(String buttonId);
	}

	public static final String CLIENTS_ID = "clients";
	public static final String ACCOUNTS_ID = "accounts";
	public static final String TRANSACTIONS_ID = "transactions";
	public static final String USERS_ID = "users";

	private ToggleButton selected;
	private final MenuItemClickHandler clickHandler;
	private UserContext userContext;

	public MenuPanel(MenuItemClickHandler clickHandler, UserContext userContext) {
		super();
		this.clickHandler = clickHandler;
		this.userContext = userContext;
		paint();
	}

	private void paint() {
		VerticalLayoutContainer verticalLayoutContainer = new VerticalLayoutContainer();

		ToggleButton clientsViewButton = new ToggleButton("Клиенты");
		ToggleButton accountsViewButton = new ToggleButton("Счета");
		ToggleButton transactionsViewButton = new ToggleButton("Транзакции");

		clientsViewButton.addSelectHandler(this);
		clientsViewButton.setId(CLIENTS_ID);
		accountsViewButton.addSelectHandler(this);
		accountsViewButton.setId(ACCOUNTS_ID);
		transactionsViewButton.addSelectHandler(this);
		transactionsViewButton.setId(TRANSACTIONS_ID);

		VerticalLayoutData verticalLayoutData = new VerticalLayoutData(1, -1, new Margins(10, 0, 0,
				0));
		verticalLayoutContainer.add(clientsViewButton, verticalLayoutData);
		verticalLayoutContainer.add(accountsViewButton, verticalLayoutData);
		verticalLayoutContainer.add(transactionsViewButton, verticalLayoutData);

		if (userContext.isSuperUser()) {
			ToggleButton usersViewButton = new ToggleButton("Пользователи");
			usersViewButton.addSelectHandler(this);
			usersViewButton.setId(USERS_ID);
			verticalLayoutContainer.add(usersViewButton, verticalLayoutData);
		}

		add(verticalLayoutContainer);
	}

	@Override
	public void onSelect(SelectEvent event) {
		if (selected != null) {
			if (selected.equals(event.getSource())) {
				selected.setValue(true);
				return;
			}
			selected.setValue(false);
		}
		selected = (ToggleButton) event.getSource();
		clickHandler.menuItemClick(selected.getId());
	}

}
