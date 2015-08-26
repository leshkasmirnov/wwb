/**
 * 
 */
package com.wildwestbank.modules.app.gwt.client.views;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.IsWidget;

/**
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public interface BeanEditor<M> extends IsWidget, Editor<M> {

	/**
	 * Инициализация.
	 * 
	 * @param bean
	 */
	public void init(M bean);

	/**
	 * Копирование всех значений из полей ui в поля бина.
	 * 
	 * @return
	 */
	public M flush();

	/**
	 * Валидация.
	 * 
	 * @return
	 */
	boolean validate();
}
