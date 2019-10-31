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
    private IMenu parentMenu;

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

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public IMenu getParentMenu() {
        return parentMenu;
    }

    @Override
    public IMenuOption[] getOptions() {
        return options.toArray(new IMenuOption[options.size()]);
    }

    @Override
    public int[] getValidOptionNumbers() {
        return options.stream().mapToInt(IMenuOption::getNumber).toArray();
    }

    @Override
    public IMenuOption getOptionForNumber(int optionNumber) {
        return options.stream().filter(i -> i.getNumber() == optionNumber).findFirst().get(); // Lets hope we will always find a number
    }

    @Override
    public void addOption(IMenuOption option) {
        options.add(option);
    }

    @Override
    public int nextOptionNumber() {
        return options.size() + 1;
    }

    @Override
    public String render() {

        StringBuilder sb = new StringBuilder("Nacházíte se v menu: "); // TODO: Use the IMenuView methods?
        sb.append(title);
        sb.append("\n");
        sb.append("\n");
        sb.append(description);
        sb.append("\n");

        return ui.getMenuView().formatMenu(this); //sb.toString(); //
    }

    @Override
    public IAppLogic getLogic() {
        return logic;
    }

    @Override
    public IUserInterface getParentInterface() {
        return ui;
    }

    @Override
    public boolean isSystemMenu() {
        return false; // TODO: How do I know is this is a system menu?
    }

    @Override
    public MenuType getType() {
        return MenuType.USER; // TODO: How do I know what type this is?
    }

    @Override
    public IFormField[] getFormFields() {
        return form.getFormFields();
    }

    @Override
    public void addFormField(IFormField field) {
        form.addFormField(field);
    }

    @Override
    public String renderFormField(IFormField formField) {
        return formField.getLabel() + ": ";
    }

    @Override
    public boolean isForm() {
        return false; // TODO: How do I know is this is a form?
    }
}
