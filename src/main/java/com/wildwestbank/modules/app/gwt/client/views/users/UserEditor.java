/**
 * 
 */
package com.wildwestbank.modules.app.gwt.client.views.users;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.PasswordField;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.wildwestbank.modules.app.gwt.client.views.BeanEditor;
import com.wildwestbank.modules.common.db.model.User;

/**
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public class UserEditor implements BeanEditor<User> {

	private static final int LABEL_WIDTH = 150;

	public interface Driver extends SimpleBeanEditorDriver<User, UserEditor> {
	}

	private Driver driver = GWT.<Driver> create(Driver.class);

	TextField name = new TextField();
	private PasswordField passwordField = new PasswordField();
	private PasswordField confirmPasswordField = new PasswordField();
	CheckBox active = new CheckBox();
	CheckBox superUser = new CheckBox();
	TextField firstName = new TextField();
	TextField lastName = new TextField();
	TextField middleName = new TextField();
	TextField tabNumber = new TextField();

	@Override
	public Widget asWidget() {
		VerticalLayoutContainer verticalLayoutContainer = new VerticalLayoutContainer();

		FieldLabel nameField = new FieldLabel(name, "Логин");
		FieldLabel passwordField = new FieldLabel(this.passwordField, "Пароль");
		FieldLabel confirmPasswordField = new FieldLabel(this.confirmPasswordField, "Подтверждение");
		FieldLabel activeField = new FieldLabel(active, "Активен");
		FieldLabel superUserField = new FieldLabel(superUser, "Суперпользователь");
		FieldLabel firstNameField = new FieldLabel(firstName, "Фамилия");
		FieldLabel lastNameField = new FieldLabel(lastName, "Имя");
		FieldLabel middleNameField = new FieldLabel(middleName, "Отчество");
		FieldLabel tabNumberField = new FieldLabel(tabNumber, "Табельный номер");

		nameField.setLabelWidth(LABEL_WIDTH);
		passwordField.setLabelWidth(LABEL_WIDTH);
		confirmPasswordField.setLabelWidth(LABEL_WIDTH);
		activeField.setLabelWidth(LABEL_WIDTH);
		superUserField.setLabelWidth(LABEL_WIDTH);
		firstNameField.setLabelWidth(LABEL_WIDTH);
		lastNameField.setLabelWidth(LABEL_WIDTH);
		middleNameField.setLabelWidth(LABEL_WIDTH);
		tabNumberField.setLabelWidth(LABEL_WIDTH);

		VerticalLayoutData layoutData = new VerticalLayoutData(1, -1);
		verticalLayoutContainer.add(nameField, layoutData);
		verticalLayoutContainer.add(passwordField, layoutData);
		verticalLayoutContainer.add(confirmPasswordField, layoutData);
		verticalLayoutContainer.add(activeField, layoutData);
		verticalLayoutContainer.add(superUserField, layoutData);
		verticalLayoutContainer.add(firstNameField, layoutData);
		verticalLayoutContainer.add(lastNameField, layoutData);
		verticalLayoutContainer.add(middleNameField, layoutData);
		verticalLayoutContainer.add(tabNumberField, layoutData);

		return verticalLayoutContainer;
	}

	@Override
	public void init(User bean) {
		driver.initialize(this);
		driver.edit(bean);
	}

	@Override
	public User flush() {
		User user = driver.flush();

		if (!isEmptyString(passwordField.getValue())
				&& !isEmptyString(confirmPasswordField.getValue())
				&& passwordField.getValue().equals(confirmPasswordField.getValue())) {
			user.setPlainPassword(passwordField.getValue());
		}
		return user;
	}

	private boolean isEmptyString(String str) {
		return str == null || "".equals(str);
	}

}
