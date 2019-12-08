package cz.ucl.ui.cli.menu.user.settings.tags;

import cz.ucl.logic.app.entities.definition.ITag;
import cz.ucl.ui.cli.menu.Menu;
import cz.ucl.ui.cli.menu.MenuOption;
import cz.ucl.ui.definition.menu.IMenu;

public class TagDetailMenu extends Menu {

    private int taskId;

    public TagDetailMenu(IMenu parentMenu, String title, int taskId) {
        super(parentMenu, "tag_detail", title);
        this.taskId = taskId;
    }

    @Override
    protected void build() {
        ITag tag = logic.getTagById(taskId);
        setDescription(ui.getTagView().formatTag(tag));

        IMenu editMenu = ui.getMenuFactory().createEditTagMenu(this, taskId);
        addOption(new MenuOption(nextOptionNumber(), editMenu));

        IMenu deleteMenu = ui.getMenuFactory().createDeleteTagMenu(this, taskId);
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
