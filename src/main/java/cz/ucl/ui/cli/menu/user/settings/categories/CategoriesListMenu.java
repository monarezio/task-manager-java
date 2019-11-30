package cz.ucl.ui.cli.menu.user.settings.categories;

import cz.ucl.logic.app.entities.definition.ICategory;
import cz.ucl.logic.app.entities.definition.ITag;
import cz.ucl.ui.cli.menu.Menu;
import cz.ucl.ui.cli.menu.MenuOption;
import cz.ucl.ui.definition.menu.IMenu;

public class CategoriesListMenu extends Menu {
    public CategoriesListMenu(IMenu parentMenu, String title) {
        super(parentMenu, "categories_list", title);
    }

    @Override
    protected void build() {
        ICategory[] categories = logic.getAllCategories();

        StringBuilder sb = new StringBuilder("Seznam všech uživatelských tagů.");
        if(categories.length == 0)
            sb.append("\n\nSeznam klategorií je prazdný.");

        setDescription(sb.toString());

        for (ICategory category : categories) {
            IMenu categoryDetailMenu = ui.getMenuFactory().createCategoryDetailMenu(this, category);
            addOption(new MenuOption(nextOptionNumber(), categoryDetailMenu));
        }

        IMenu backMenu = ui.getMenuFactory().createBackMenu(this);

        addOption(new MenuOption(nextOptionNumber(), backMenu));
    }

    @Override
    public void initialize() {
        clearOptions();
        build();
    }
}
