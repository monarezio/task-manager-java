package cz.ucl.ui.cli.menu;

import cz.ucl.ui.definition.menu.IMenu;

public abstract class FormMenu extends Menu {
    public FormMenu(IMenu parentMenu, String identifier, String title) {
        super(parentMenu, identifier, title);

        defineForm();
    }

    /** This method should be used for definition of form fields */
    protected abstract void defineForm();

    @Override
    public boolean isForm() {
        return true;
    }
}
