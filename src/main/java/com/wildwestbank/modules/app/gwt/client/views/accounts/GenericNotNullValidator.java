/**
 * 
 */
package com.wildwestbank.modules.app.gwt.client.views.accounts;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.sencha.gxt.widget.core.client.form.Validator;
import com.sencha.gxt.widget.core.client.form.error.DefaultEditorError;

/**
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
public class GenericNotNullValidator<T> implements Validator<T> {

	@Override
	public List<EditorError> validate(Editor<T> editor, T value) {
		List<EditorError> errors = null;
		if (value == null) {
			DefaultEditorError defaultEditorError = new DefaultEditorError(editor,
					"Необходимо указать значение!", value);
			errors = new ArrayList<>(1);
			errors.add(defaultEditorError);
		}
		return errors;
	}

}
