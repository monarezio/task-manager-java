package cz.ucl.ui.cli.menu.user.settings.tags;

import cz.ucl.logic.app.entities.definition.ITag;
import cz.ucl.ui.cli.menu.Menu;
import cz.ucl.ui.cli.menu.MenuOption;
import cz.ucl.ui.definition.menu.IMenu;

import java.util.Arrays;

public class TagsRootMenu extends Menu {
    public TagsRootMenu(IMenu parentMenu, String title) {
        super(parentMenu, "tags_menu", title);
    }

    @Override
    protected void build() {
        setDescription("Administrace uživatelských tagů.");

        IMenu tagsListMenu = ui.getMenuFactory().createTagsListMenu(this);
        IMenu addTagForm = ui.getMenuFactory().createAddTagFormMenu(this);
        //IMenu deleteTagForm = ui.getMenuFactory().createDeleteTagFormMenu(this); TODO: Maybe in the future

        IMenu backMenu = ui.getMenuFactory().createBackMenu(this);

        addOption(new MenuOption(nextOptionNumber(), tagsListMenu));
        addOption(new MenuOption(nextOptionNumber(), addTagForm));
        //addOption(new MenuOption(nextOptionNumber(), deleteTagForm));
        addOption(new MenuOption(nextOptionNumber(), backMenu));
    }
}
