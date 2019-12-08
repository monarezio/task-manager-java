package cz.ucl.ui.cli.menu.user.settings.tags;

import cz.ucl.logic.exceptions.TagInUseException;
import cz.ucl.ui.definition.menu.ActionMenu;
import cz.ucl.ui.definition.menu.IMenu;

public class TagDeleteActionMenu extends ActionMenu {

    private int tagId;

    public TagDeleteActionMenu(IMenu parentMenu, String title, int tagId) {
        super(parentMenu, "tag_delete", title, parentMenu.getParentMenu());
        this.tagId = tagId;
    }

    @Override
    public void action() {
        try {
            logic.destroyTag(tagId);
        } catch (TagInUseException e) {
            ui.drawError("Please first delete all tasks used by this tag");
        }
    }
}
