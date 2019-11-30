package cz.ucl.ui.cli.menu.user.settings.categories;

import cz.ucl.logic.app.entities.definition.Color;
import cz.ucl.ui.cli.forms.FormField;
import cz.ucl.ui.cli.menu.FormMenu;
import cz.ucl.ui.cli.menu.MenuOption;
import cz.ucl.ui.definition.forms.FormFieldType;
import cz.ucl.ui.definition.menu.IMenu;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CategoryAddFormMenu extends FormMenu {
    public CategoryAddFormMenu(IMenu parentMenu, String title) {
        super(parentMenu, "add_category", title);
    }

    @Override
    protected void defineForm() {
        addFormField(new FormField("title", "Název", FormFieldType.TEXTUAL));
        addFormField(new FormField("color", "Barva", FormFieldType.TEXTUAL, false));
    }

    @Override
    protected void build() {
        String colors = Stream.of(Color.values())
                .map(Enum::toString)
                .collect(Collectors.joining(", "));

        setDescription("Pro přidání kategorie je třeba zadat název.\n\nMožné barvy jsou: " + colors);

        IMenu backMenu = ui.getMenuFactory().createBackMenu(this);
        IMenu fillMenu = ui.getMenuFactory().createFillFormMenu(this);

        addOption(new MenuOption(nextOptionNumber(), backMenu));
        addOption(new MenuOption(nextOptionNumber(), fillMenu));
    }
}
