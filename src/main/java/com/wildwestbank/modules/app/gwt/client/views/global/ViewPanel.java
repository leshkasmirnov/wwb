/**
 * 
 */
package com.wildwestbank.modules.app.gwt.client.views.global;

import com.sencha.gxt.widget.core.client.ContentPanel;
import com.wildwestbank.modules.app.gwt.client.views.BaseView;

/**
 * Контентная область.
 * 
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public class ViewPanel extends ContentPanel {

	private BaseView currentView;

	public void setView(BaseView view) {
		if (view.equals(currentView)) {
			return;
		}
		if (currentView != null) {
			remove(currentView);
		}
		add(view);
		currentView = view;
		setHeadingText(view.getHeaderText());
	}
}
