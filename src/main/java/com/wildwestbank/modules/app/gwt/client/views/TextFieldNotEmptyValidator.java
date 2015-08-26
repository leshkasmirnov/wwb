/**
 * 
 */
package com.wildwestbank.modules.app.gwt.client.views;

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
public class TextFieldNotEmptyValidator implements Validator<String> {

	@Override
	public List<EditorError> validate(Editor<String> editor, String value) {
		List<EditorError> errors = null;
		if (value == null || "".equals(value)) {
			DefaultEditorError defaultEditorError = new DefaultEditorError(editor,
					"Значение не должно быть пустым!", value);
			errors = new ArrayList<>(1);
			errors.add(defaultEditorError);
		}
		return errors;
	}

}
