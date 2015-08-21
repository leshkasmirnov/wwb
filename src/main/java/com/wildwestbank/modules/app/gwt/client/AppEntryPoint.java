package com.wildwestbank.modules.app.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.container.Viewport;
import com.wildwestbank.modules.app.gwt.client.views.global.GlobalUI;
import com.wildwestbank.modules.common.gwt.client.rpc.SecurityRpc;
import com.wildwestbank.modules.common.security.UserContext;

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
		SecurityRpc.Async.getUserContext(new AsyncCallback<UserContext>() {

			@Override
			public void onSuccess(UserContext result) {
				Viewport viewport = new Viewport();
				GlobalUI mainLayoutContainer = new GlobalUI(result);

				viewport.add(mainLayoutContainer);
				RootPanel.get().add(viewport);
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		});

	}
}
