package cz.ucl.ui.cli.forms;

import cz.ucl.ui.definition.forms.FormFieldType;
import cz.ucl.ui.definition.forms.IFormField;

public class FormField implements IFormField {

    private String identifier;
    private String title;
    private String label;
    private FormFieldType type;

    private FormField(String identifier, String title, String label, FormFieldType type, boolean isRequired) {
        this.identifier = identifier;
        this.title = title;
        this.label = label;
        this.type = type;
        this.isRequired = isRequired;
    }

    private boolean isRequired;

    public FormField(String identifier, String title, FormFieldType type, boolean isRequired) {
        this(identifier, title, "Prosím zadejte " + title.toLowerCase(), type, isRequired);
    }

    public FormField(String identifier, String title, FormFieldType type) {
        this(identifier, title, type, true);
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public FormFieldType getType() {
        return type;
    }

    @Override
    public boolean getIsRequired() {
        return isRequired;
    }
}
