package cz.ucl.ui.cli.menu;

import cz.ucl.ui.definition.menu.IMenu;
import cz.ucl.ui.definition.menu.IMenuOption;

public class MenuOption implements IMenuOption {

    private int number;
    private String title;
    private IMenu menu;

    public MenuOption(int number, String title, IMenu menu) {
        this.number = number;
        this.title = title;
        this.menu = menu;
    }

    public MenuOption(int number, IMenu menu) {
        this.number = number;
        this.title = menu.getTitle();
        this.menu = menu;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public IMenu getMenu() {
        return menu;
    }
}
