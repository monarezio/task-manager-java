package cz.ucl.ui.definition.menu;

import cz.ucl.ui.cli.menu.Menu;

abstract public class ActionMenu extends Menu {

    private IMenu targetMenu;

    public ActionMenu(IMenu parentMenu, String identifier, String title, IMenu targetMenu) {
        super(parentMenu, identifier, title);
        this.targetMenu = targetMenu;
    }

    @Override
    public boolean isSystemMenu() {
        return true;
    }

    @Override
    public MenuType getType() {
        return MenuType.SYSTEM_ACTION;
    }

    @Override
    protected void build() {
    }

    public IMenu getTargetMenu() {
        return this.targetMenu;
    }

    public abstract void action();
}
