package com.wildwestbank.modules.app.gwt.client.views.clients;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.wildwestbank.modules.app.gwt.client.views.BeanEditor;
import com.wildwestbank.modules.common.db.model.Address;

/**
 * 
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public class AddressEditor implements BeanEditor<Address> {

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

		layoutContainer.add(new FieldLabel(region, "Регион"), layoutData);
		layoutContainer.add(new FieldLabel(city, "Город"), layoutData);
		layoutContainer.add(new FieldLabel(street, "Улица"), layoutData);
		layoutContainer.add(new FieldLabel(house, "Дом"), layoutData);
		layoutContainer.add(new FieldLabel(building, "Корпус"), layoutData);
		layoutContainer.add(new FieldLabel(flat, "Квартира"), layoutData);
		layoutContainer.add(new FieldLabel(zipCode, "Индекс"), layoutData);

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

}
