package cz.ucl.ui.cli.menu.user.settings.tags;

import cz.ucl.logic.app.entities.definition.ITag;
import cz.ucl.ui.cli.menu.Menu;
import cz.ucl.ui.cli.menu.MenuOption;
import cz.ucl.ui.definition.menu.IMenu;

public class TagDetailMenu extends Menu {

    private int tagId;

    public TagDetailMenu(IMenu parentMenu, String title, int tagId) {
        super(parentMenu, "tag_detail", title);
        this.tagId = tagId;
    }

    @Override
    protected void build() {
        ITag tag = logic.getTagById(tagId);

        StringBuilder sb = new StringBuilder("Detail tagu:\n");
        sb.append("ID: ");
        sb.append(tag.getId());
        sb.append("Název: ");
        sb.append(tag.getTitle());
        sb.append("Barva: ");
        sb.append(tag.getColor());

        setDescription(sb.toString());

        IMenu backMenu = ui.getMenuFactory().createBackMenu(this);
        addOption(new MenuOption(nextOptionNumber(), backMenu));
    }

    @Override
    public void initialize() {
        clearOptions();
        build();
    }
}
