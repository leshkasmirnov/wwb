/**
 * 
 */
package com.wildwestbank.modules.app.gwt.client.views.accounts;

import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.sencha.gxt.cell.core.client.form.ValueBaseInputCell;
import com.wildwestbank.modules.common.db.model.Person;

/**
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public class PersonSelectCell extends ValueBaseInputCell<Person> {

	public PersonSelectCell(
			com.sencha.gxt.cell.core.client.form.ValueBaseInputCell.ValueBaseFieldAppearance appearance) {
		super(appearance);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(com.google.gwt.cell.client.Cell.Context context, Person value,
			SafeHtmlBuilder sb) {
		// TODO Auto-generated method stub

	}

}
