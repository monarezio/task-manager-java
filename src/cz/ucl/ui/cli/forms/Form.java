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

    // TODO

    @Override
    public String renderFormField(IFormField formField) {
        return menu.getParentInterface().getFormView().formatFormField(formField);
    }
}
