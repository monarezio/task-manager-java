package cz.ucl.ui.definition.views;

import cz.ucl.ui.definition.forms.IFormField;

/** Views are used only for formatting purposes. They are stateless. */
public interface IFormView {
    String formatFormField(IFormField field);
}
