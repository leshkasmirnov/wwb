package com.wildwestbank.modules.app.gwt.client.views.clients;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.i18n.client.Messages;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.wildwestbank.modules.app.gwt.client.views.BeanEditor;
import com.wildwestbank.modules.app.gwt.client.views.TextFieldNotEmptyValidator;
import com.wildwestbank.modules.common.db.model.Address;

/**
 * 
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public class AddressEditor implements BeanEditor<Address> {

	public interface AddressEditorMessages extends Messages {

		static final AddressEditorMessages MESSSAGES = GWT.create(AddressEditorMessages.class);

		@DefaultMessage("Регион")
		String region();

		@DefaultMessage("Город")
		String city();

		@DefaultMessage("Улица")
		String street();

		@DefaultMessage("Дом")
		String house();

		@DefaultMessage("Корпус")
		String building();

		@DefaultMessage("Квартира")
		String flat();

		@DefaultMessage("Индекс")
		String zipCode();

	}

	public interface Driver extends SimpleBeanEditorDriver<Address, AddressEditor> {
	}

	private Driver driver = GWT.<Driver> create(Driver.class);

	TextField region = new TextField();
	TextField city = new TextField();
	TextField street = new TextField();
	TextField house = new TextField();
	TextField building = new TextField();
	TextField flat = new TextField();
	TextField zipCode = new TextField();

	@Override
	public Widget asWidget() {
		VerticalLayoutContainer layoutContainer = new VerticalLayoutContainer();
		VerticalLayoutData layoutData = new VerticalLayoutData(1, -1);

		region.addValidator(new TextFieldNotEmptyValidator());
		city.addValidator(new TextFieldNotEmptyValidator());
		street.addValidator(new TextFieldNotEmptyValidator());
		house.addValidator(new TextFieldNotEmptyValidator());
		flat.addValidator(new TextFieldNotEmptyValidator());
		zipCode.addValidator(new ZipCodeValidator());

		layoutContainer.add(new FieldLabel(region, AddressEditorMessages.MESSSAGES.region()),
				layoutData);
		layoutContainer.add(new FieldLabel(city, AddressEditorMessages.MESSSAGES.city()),
				layoutData);
		layoutContainer.add(new FieldLabel(street, AddressEditorMessages.MESSSAGES.street()),
				layoutData);
		layoutContainer.add(new FieldLabel(house, AddressEditorMessages.MESSSAGES.house()),
				layoutData);
		layoutContainer.add(new FieldLabel(building, AddressEditorMessages.MESSSAGES.building()),
				layoutData);
		layoutContainer.add(new FieldLabel(flat, AddressEditorMessages.MESSSAGES.flat()),
				layoutData);
		layoutContainer.add(new FieldLabel(zipCode, AddressEditorMessages.MESSSAGES.zipCode()),
				layoutData);

		return layoutContainer;
	}

	@Override
	public void init(Address bean) {
		driver.initialize(this);
		driver.edit(bean);
	}

	@Override
	public Address flush() {
		return driver.flush();
	}

	@Override
	public boolean validate() {
		return region.validate() && city.validate() && street.validate() && house.validate()
				&& flat.validate() && zipCode.validate();
	}
}
