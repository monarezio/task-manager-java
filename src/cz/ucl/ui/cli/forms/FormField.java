package cz.ucl.ui.cli.forms;

import cz.ucl.ui.definition.forms.FormFieldType;
import cz.ucl.ui.definition.forms.IFormField;

public class FormField implements IFormField {
    // TODO

    public FormField(String identifier, String title, FormFieldType type) {
        this(identifier, title, "Prosím zadejte " + title.toLowerCase(), type, true);
    }

    // TODO
}
