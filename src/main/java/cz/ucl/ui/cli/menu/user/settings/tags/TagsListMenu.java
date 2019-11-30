package cz.ucl.ui.cli.menu.user.settings.tags;

import cz.ucl.logic.app.entities.definition.ITag;
import cz.ucl.ui.cli.menu.Menu;
import cz.ucl.ui.cli.menu.MenuOption;
import cz.ucl.ui.definition.menu.IMenu;

public class TagsListMenu extends Menu {
    public TagsListMenu(IMenu parentMenu, String title) {
        super(parentMenu, "tags_list", title);
    }

    @Override
    protected void build() {
        ITag[] tags = logic.getAllTags();

        setDescription("Seznam všech uživatelských tagů.");

        for (ITag tag : tags) {
            IMenu tagDetailMenu = ui.getMenuFactory().createTagDetailMenu(this, tag);
            addOption(new MenuOption(nextOptionNumber(), tagDetailMenu));
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
