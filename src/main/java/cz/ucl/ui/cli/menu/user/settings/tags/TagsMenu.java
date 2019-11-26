package cz.ucl.ui.cli.menu.user.settings.tags;

import cz.ucl.logic.app.entities.definition.ITag;
import cz.ucl.ui.cli.menu.Menu;
import cz.ucl.ui.cli.menu.MenuOption;
import cz.ucl.ui.definition.menu.IMenu;

public class TagsMenu extends Menu {
    public TagsMenu(IMenu parentMenu, String title) {
        super(parentMenu, "tags_menu", title);
    }

    @Override
    protected void build() {
        ITag[] tags = logic.getAllTags();
        setDescription("Administrace uživatelských tagů.\n" + ui.getTagView().formatTagList(tags));

        IMenu addTagForm = ui.getMenuFactory().createAddTagFormMenu(this);
        IMenu deleteTagForm = ui.getMenuFactory().createDeleteTagFormMenu(this);
        IMenu backMenu = ui.getMenuFactory().createBackMenu(this);

        addOption(new MenuOption(nextOptionNumber(), addTagForm));
        addOption(new MenuOption(nextOptionNumber(), deleteTagForm));
        addOption(new MenuOption(nextOptionNumber(), backMenu));
    }

    @Override
    public void initialize() {
        clearOptions();
        build();
    }
}
