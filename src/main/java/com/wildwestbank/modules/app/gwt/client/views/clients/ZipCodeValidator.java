/**
 * 
 */
package com.wildwestbank.modules.app.gwt.client.views.clients;

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
public class ZipCodeValidator implements Validator<String> {

	@Override
	public List<EditorError> validate(Editor<String> editor, String value) {
		List<EditorError> errors = null;
		if (value == null || "".equals(value) || (value != null && value.length() != 6)) {
			DefaultEditorError defaultEditorError = new DefaultEditorError(editor,
					"Индекс должен состоять из 6 символов!", value);
			errors = new ArrayList<>(1);
			errors.add(defaultEditorError);
		}
		return errors;
	}

}
