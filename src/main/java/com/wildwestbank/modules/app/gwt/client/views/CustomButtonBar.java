/**
 * 
 */
package com.wildwestbank.modules.app.gwt.client.views;

import java.util.HashMap;
import java.util.Map;

import com.sencha.gxt.widget.core.client.button.ButtonBar;
import com.sencha.gxt.widget.core.client.button.TextButton;

/**
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public class CustomButtonBar extends ButtonBar {

	public class PredefinedButtons {
		public static final String ADD = "add";
		public static final String EDIT = "edit";
		public static final String DELETE = "delete";
	}

	private Map<String, TextButton> buttons = new HashMap<>();

	public void addButton(String id, TextButton button) {
		buttons.put(id, button);
		add(button);
	}

	public TextButton getButton(String id) {
		return buttons.get(id);
	}

	public void removeButton(String id) {
		TextButton button = getButton(id);
		if (button != null) {
			remove(button);
		}
	}
}
