/**
 * 
 */
package com.wildwestbank.gwt.login.client.views;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
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
import com.wildwestbank.gwt.common.shared.AuthenticationToken;

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

	private final AuthenticationListener authenticationListener;
	private final ContentPanel panel;
	final TextField userNameField = new TextField();
	final PasswordField passwordField = new PasswordField();

	public AuthenticationForm(AuthenticationListener authenticationListener) {
		super();
		this.authenticationListener = authenticationListener;
		panel = new ContentPanel();
		paint();
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
				AuthenticationToken token = new AuthenticationToken();
				token.setUserName(userNameField.getValue());
				token.setPassword(passwordField.getValue());
				authenticationListener.onAuthenticate(token);
			}
		});

		VerticalLayoutData layoutData = new VerticalLayoutData(1, -1,
				new Margins(5));
		HorizontalLayoutContainer bottom = new HorizontalLayoutContainer();

		bottom.add(new VerticalLayoutContainer(), new HorizontalLayoutData(1,
				-1));
		bottom.add(okButtnon);

		container.add(new FieldLabel(userNameField, "Login"), layoutData);
		container.add(new FieldLabel(passwordField, "Password"), layoutData);
		container.add(bottom, layoutData);

		panel.add(container);
	}

	@Override
	public Widget asWidget() {
		return panel;
	}

}
