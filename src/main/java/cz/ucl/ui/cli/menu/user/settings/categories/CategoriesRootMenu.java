package cz.ucl.ui.cli.menu.user.settings.categories;

import cz.ucl.ui.cli.menu.Menu;
import cz.ucl.ui.cli.menu.MenuOption;
import cz.ucl.ui.definition.menu.IMenu;

public class CategoriesRootMenu extends Menu {
    public CategoriesRootMenu(IMenu parentMenu, String title) {
        super(parentMenu, "categories_menu", title);
    }

    @Override
    protected void build() {
        setDescription("Administrace uživatelských kategorií.");

        IMenu categoriesListMenu = ui.getMenuFactory().createCategoryListMenu(this);
        IMenu addCategoryForm = ui.getMenuFactory().createAddCategoryFormMenu(this);

        IMenu backMenu = ui.getMenuFactory().createBackMenu(this);

        addOption(new MenuOption(nextOptionNumber(), categoriesListMenu));
        addOption(new MenuOption(nextOptionNumber(), addCategoryForm));
        addOption(new MenuOption(nextOptionNumber(), backMenu));
    }
}
