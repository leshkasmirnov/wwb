/**
 * 
 */
package com.wildwestbank.modules.login.gwt.client.views;

import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.KeyNav;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer.HorizontalLayoutData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.PasswordField;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.wildwestbank.modules.common.security.auth.AuthenticationToken;

/**
 * Authentication form.
 * 
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public class AuthenticationForm implements IsWidget {

	public interface AuthenticationListener {
		void onAuthenticate(AuthenticationToken token);
	}

	private AuthenticationListener authenticationListener;
	private final ContentPanel panel;
	final TextField userNameField = new TextField();
	final PasswordField passwordField = new PasswordField();
	private VerticalLayoutContainer errorArea;

	public AuthenticationForm() {
		super();
		panel = new ContentPanel();
		paint();
	}

	public void setListener(AuthenticationListener authenticationListener) {
		this.authenticationListener = authenticationListener;
	}

	private void paint() {
		panel.setBorders(true);
		panel.setHeadingText("Authentication");

		panel.setPixelSize(400, 140);

		VerticalLayoutContainer container = new VerticalLayoutContainer();

		TextButton okButtnon = new TextButton("OK");

		okButtnon.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				authenticate();
			}
		});

		VerticalLayoutData layoutData = new VerticalLayoutData(1, -1, new Margins(5));
		HorizontalLayoutContainer bottom = new HorizontalLayoutContainer();

		errorArea = new VerticalLayoutContainer();
		bottom.add(errorArea, new HorizontalLayoutData(1, -1));
		bottom.add(okButtnon);
		new KeyNav(panel.getWidget()) {
			@Override
			public void onEnter(NativeEvent evt) {
				authenticate();
			}
		};

		container.add(new FieldLabel(userNameField, "Login"), layoutData);
		container.add(new FieldLabel(passwordField, "Password"), layoutData);
		container.add(bottom, layoutData);

		panel.add(container);
	}

	private void authenticate() {
		errorArea.clear();
		AuthenticationToken token = new AuthenticationToken();
		token.setUserName(userNameField.getValue());
		token.setPassword(passwordField.getValue());
		authenticationListener.onAuthenticate(token);
	}

	@Override
	public Widget asWidget() {
		userNameField.focus();
		return panel;
	}

	public void addError(String errorMessage) {
		errorArea.add(new Label(errorMessage));
	}

}
