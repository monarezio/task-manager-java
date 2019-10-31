package cz.ucl.ui.cli.forms;

import cz.ucl.ui.definition.forms.IForm;
import cz.ucl.ui.definition.forms.IFormField;
import cz.ucl.ui.definition.menu.IMenu;

import java.util.ArrayList;
import java.util.List;

public class Form implements IForm {
    private List<IFormField> fields;
    private IMenu menu;

    public Form(IMenu parentMenu) {
        this.menu = parentMenu;
        this.fields = new ArrayList<>();
    }

    @Override
    public IFormField[] getFormFields() {
        return fields.toArray(new IFormField[fields.size()]);
    }

    @Override
    public void addFormField(IFormField field) {
        fields.add(field);
    }

    @Override
    public String renderFormField(IFormField formField) {
        return menu.getParentInterface().getFormView().formatFormField(formField);
    }

    @Override
    public boolean isForm() {
        return true; // TODO: What?
    }
}
