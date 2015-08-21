package com.wildwestbank.modules.login.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.container.CenterLayoutContainer;
import com.sencha.gxt.widget.core.client.container.Viewport;
import com.wildwestbank.modules.common.gwt.client.rpc.SecurityRpc;
import com.wildwestbank.modules.common.security.auth.AuthenticationResult;
import com.wildwestbank.modules.common.security.auth.AuthenticationResult.AuthenticationState;
import com.wildwestbank.modules.common.security.auth.AuthenticationToken;
import com.wildwestbank.modules.login.gwt.client.views.AuthenticationForm;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 * 
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public class LoginEntryPoint implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		Viewport viewport = new Viewport();
		CenterLayoutContainer layoutContainer = new CenterLayoutContainer();
		final AuthenticationForm form = new AuthenticationForm();
		form.setListener(new AuthenticationForm.AuthenticationListener() {

			@Override
			public void onAuthenticate(AuthenticationToken token) {
				SecurityRpc.Async.authenticate(token, new AsyncCallback<AuthenticationResult>() {

					@Override
					public void onSuccess(AuthenticationResult result) {
						if (AuthenticationState.OK == result.getState()) {
							Window.Location.reload();
						} else {
							form.addError(result.getMessage());
						}
					}

					@Override
					public void onFailure(Throwable caught) {
					}
				});
			}

		});

		layoutContainer.add(form);
		layoutContainer.forceLayout();

		viewport.add(layoutContainer);
		RootPanel.get().add(viewport);
	}
}
