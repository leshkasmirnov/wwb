package com.wildwestbank.gwt.login.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.container.CenterLayoutContainer;
import com.sencha.gxt.widget.core.client.container.Viewport;
import com.wildwestbank.gwt.common.client.rpc.SecurityRpc;
import com.wildwestbank.gwt.common.shared.AuthenticationResult;
import com.wildwestbank.gwt.common.shared.AuthenticationToken;
import com.wildwestbank.gwt.common.shared.AuthenticationResult.AuthenticationState;
import com.wildwestbank.gwt.login.client.views.AuthenticationForm;

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
		AuthenticationForm form = new AuthenticationForm(
				new AuthenticationForm.AuthenticationListener() {

					@Override
					public void onAuthenticate(AuthenticationToken token) {
						SecurityRpc.Async.authenticate(token,
								new AsyncCallback<AuthenticationResult>() {

									@Override
									public void onSuccess(AuthenticationResult result) {
										if (AuthenticationState.OK == result.getState()) {
											Window.Location.reload();
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
