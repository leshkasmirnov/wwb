/**
 * 
 */
package com.wildwestbank.modules.app.gwt.client.views.global;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer.HorizontalLayoutData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.wildwestbank.modules.common.gwt.client.rpc.SecurityRpc;
import com.wildwestbank.modules.common.security.UserContext;
import com.wildwestbank.modules.common.security.auth.AuthenticationResult;

/**
 * Верхняя панель.
 * 
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public class TopPanel extends ContentPanel {

	public TopPanel(UserContext userContext) {
		super();
		setHeaderVisible(false);
		HorizontalLayoutContainer horizontalLayoutContainer = new HorizontalLayoutContainer();
		VerticalLayoutContainer verticalLayoutContainer = new VerticalLayoutContainer();

		horizontalLayoutContainer.add(verticalLayoutContainer, new HorizontalLayoutData(1, -1));
		HorizontalLayoutData layoutData = new HorizontalLayoutData();
		layoutData.setMargins(new Margins(5, 5, 0, 0));
		horizontalLayoutContainer.add(new Label("Вы вошли как: " + userContext.getFio()),
				layoutData);

		TextButton exitBtn = new TextButton("Выход");
		exitBtn.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				SecurityRpc.Async.logOut(new AsyncCallback<AuthenticationResult>() {

					@Override
					public void onSuccess(AuthenticationResult result) {
						Window.Location.reload();

					}

					@Override
					public void onFailure(Throwable caught) {
					}
				});

			}
		});
		horizontalLayoutContainer.add(exitBtn, layoutData);
		add(horizontalLayoutContainer);
	}

}
