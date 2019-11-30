package cz.ucl.ui.cli.menu.user.settings.categories;

import cz.ucl.ui.definition.menu.ActionMenu;
import cz.ucl.ui.definition.menu.IMenu;

public class CategoryDeleteActionMenu extends ActionMenu {
    private int categoryId;

    public CategoryDeleteActionMenu(IMenu parentMenu, String identifier, String title, int categoryId) {
        super(parentMenu, identifier, title, parentMenu.getParentMenu()); // TODO: fix targetMenu
        this.categoryId = categoryId;
    }

    @Override
    public void action() {
        logic.destroyCategory(categoryId);
    }
}
