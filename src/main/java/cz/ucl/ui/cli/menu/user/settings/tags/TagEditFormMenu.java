package cz.ucl.ui.cli.menu.user.settings.tags;

import cz.ucl.logic.app.entities.definition.Color;
import cz.ucl.logic.app.entities.definition.ITag;
import cz.ucl.ui.cli.forms.FormField;
import cz.ucl.ui.cli.menu.FormMenu;
import cz.ucl.ui.cli.menu.Menu;
import cz.ucl.ui.cli.menu.MenuOption;
import cz.ucl.ui.definition.forms.FormFieldType;
import cz.ucl.ui.definition.menu.IMenu;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TagEditFormMenu extends Menu { // TODO: use FormMenu

    private final int tagId;

    public TagEditFormMenu(IMenu parentMenu, String title, int tagId) {
        super(parentMenu, "edit_tag", title);
        this.tagId = tagId;
        defineForm();
    }

    private void defineForm() {
        ITag tag = logic.getTagById(tagId);

        addFormField(new FormField("title", "Název (" + tag.getTitle() + ")", FormFieldType.TEXTUAL));
        addFormField(new FormField("color", "Barva (" + tag.getColor() + ")", FormFieldType.TEXTUAL, false));
    }

    @Override
    protected void build() {
        String colors = Stream.of(Color.values())
                .map(Enum::toString)
                .collect(Collectors.joining(", "));

        setDescription("Pro úpravu tagu je třeba zadat název.\n\nMožné barvy jsou: " + colors);

        IMenu backMenu = ui.getMenuFactory().createBackMenu(this);
        IMenu fillMenu = ui.getMenuFactory().createFillFormMenu(this);

        addOption(new MenuOption(nextOptionNumber(), backMenu));
        addOption(new MenuOption(nextOptionNumber(), fillMenu));
    }

    public int getTagId() {
        return tagId;
    }

    @Override
    public boolean isForm() {
        return true;
    }
}
