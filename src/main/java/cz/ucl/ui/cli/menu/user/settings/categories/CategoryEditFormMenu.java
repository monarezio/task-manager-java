package cz.ucl.ui.cli.menu.user.settings.categories;

import cz.ucl.logic.app.entities.definition.Color;
import cz.ucl.logic.app.entities.definition.ICategory;
import cz.ucl.ui.cli.forms.FormField;
import cz.ucl.ui.cli.menu.Menu;
import cz.ucl.ui.cli.menu.MenuOption;
import cz.ucl.ui.definition.forms.FormFieldType;
import cz.ucl.ui.definition.menu.IMenu;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CategoryEditFormMenu extends Menu {
    private final int categoryId;

    public CategoryEditFormMenu(IMenu parentMenu, String title, int categoryId) {
        super(parentMenu, "edit_category", title);
        this.categoryId = categoryId;
        defineForm();
    }

    private void defineForm() {
        ICategory category = logic.getCategoryById(categoryId);

        addFormField(new FormField("title", "Název (" + category.getTitle() + ")", FormFieldType.TEXTUAL));
        addFormField(new FormField("color", "Barva (" + category.getColor() + ")", FormFieldType.TEXTUAL, false));
    }

    @Override
    protected void build() {
        String colors = Stream.of(Color.values())
                .map(Enum::toString)
                .collect(Collectors.joining(", "));

        setDescription("Pro úpravu kategorií je třeba zadat název.\n\nMožné barvy jsou: " + colors);

        IMenu backMenu = ui.getMenuFactory().createBackMenu(this);
        IMenu fillMenu = ui.getMenuFactory().createFillFormMenu(this);

        addOption(new MenuOption(nextOptionNumber(), backMenu));
        addOption(new MenuOption(nextOptionNumber(), fillMenu));
    }

    public int getCategoryId() {
        return categoryId;
    }

    @Override
    public boolean isForm() {
        return true;
    }
}
