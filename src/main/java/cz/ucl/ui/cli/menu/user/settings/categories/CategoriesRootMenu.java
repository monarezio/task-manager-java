package cz.ucl.ui.cli.menu.user.settings.categories;

import cz.ucl.ui.cli.menu.Menu;
import cz.ucl.ui.definition.menu.IMenu;

public class CategoriesRootMenu extends Menu {
    public CategoriesRootMenu(IMenu parentMenu, String title) {
        super(parentMenu, "categories_menu", title);
    }

    @Override
    protected void build() {
    }
}
