package cz.ucl.ui.cli.menu.user.settings.categories;

import cz.ucl.logic.app.entities.definition.ICategory;
import cz.ucl.ui.cli.menu.Menu;
import cz.ucl.ui.cli.menu.MenuOption;
import cz.ucl.ui.definition.menu.IMenu;

public class CategoryDetailMenu extends Menu {

    private final int categoryId;

    public CategoryDetailMenu(IMenu parentMenu, String title, int categoryId) {
        super(parentMenu, "category_detail", title);
        this.categoryId = categoryId;
    }

    @Override
    protected void build() {
        ICategory category = logic.getCategoryById(categoryId);
        setDescription(ui.getCategoryView().formatCategory(category));

        IMenu editMenu = ui.getMenuFactory().createEditCategoryMenu(this, categoryId);
        addOption(new MenuOption(nextOptionNumber(), editMenu));

        IMenu deleteMenu = ui.getMenuFactory().createDeleteCategoryMenu(this, categoryId);
        addOption(new MenuOption(nextOptionNumber(), deleteMenu));

        IMenu backMenu = ui.getMenuFactory().createBackMenu(this);
        addOption(new MenuOption(nextOptionNumber(), backMenu));
    }

    @Override
    public void initialize() {
        clearOptions();
        build();
    }
}
