package cz.ucl.ui.cli.menu;

import cz.ucl.logic.IAppLogic;
import cz.ucl.ui.cli.forms.Form;
import cz.ucl.ui.definition.IUserInterface;
import cz.ucl.ui.definition.forms.IForm;
import cz.ucl.ui.definition.forms.IFormField;
import cz.ucl.ui.definition.menu.IMenu;
import cz.ucl.ui.definition.menu.IMenuOption;
import cz.ucl.ui.definition.menu.MenuType;

import java.util.ArrayList;
import java.util.List;

public abstract class Menu implements IMenu {
    private String identifier;
    private String title;
    private String description;
    private List<IMenuOption> options;
    private IForm form;
    private boolean isBuilt;

    protected IUserInterface ui;
    protected IAppLogic logic;
    protected IMenu parentMenu;

    public Menu(IMenu parentMenu, String identifier, String title) {
        if (parentMenu != null) {
            this.ui = parentMenu.getParentInterface();
            this.logic = this.ui.getLogic();
        }
        this.parentMenu = parentMenu;
        this.identifier = identifier;
        this.title = title;

        this.options = new ArrayList<>();
        this.form = new Form(this);

        this.isBuilt = false;
    }

    /** This method should be used for building the menu - setting description, adding options, etc */
    protected abstract void build();

    @Override
    public void initialize() {
        if (!isBuilt) {
            build();
            isBuilt = true;
        }
    }

    // TODO
}
