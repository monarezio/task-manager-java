package cz.ucl.ui.cli.menu.user.settings;

import cz.ucl.ui.cli.menu.Menu;
import cz.ucl.ui.cli.menu.MenuOption;
import cz.ucl.ui.definition.menu.IMenu;

public class SettingsMenu extends Menu {
    public SettingsMenu(IMenu parentMenu, String title) {
        super(parentMenu, "settings_menu", title);
    }

    @Override
    protected void build() {
        setDescription("Uživatelské nastavení.");

        IMenu tagsMenu = ui.getMenuFactory().createTagsRootMenu(this);
        IMenu categoriesMenu = ui.getMenuFactory().createCategoriesRootMenu(this);
        IMenu backMenu = ui.getMenuFactory().createBackMenu(this);

        addOption(new MenuOption(nextOptionNumber(), tagsMenu));
        addOption(new MenuOption(nextOptionNumber(), backMenu));
    }
}
