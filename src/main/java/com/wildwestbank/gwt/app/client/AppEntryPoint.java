package com.wildwestbank.gwt.app.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.Viewport;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.wildwestbank.gwt.common.client.rpc.SecurityRpc;
import com.wildwestbank.gwt.common.shared.AuthenticationResult;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 * 
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public class AppEntryPoint implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		Viewport viewport = new Viewport();
		TextButton exitButton = new TextButton("Exit");
		exitButton.addSelectHandler(new SelectEvent.SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				SecurityRpc.Async.logOut(new AsyncCallback<AuthenticationResult>() {

					@Override
					public void onSuccess(AuthenticationResult result) {
						Window.Location.reload();

					}

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}
				});

			}
		});
		viewport.add(exitButton);
		RootPanel.get().add(viewport);
	}
}
