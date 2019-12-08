package cz.ucl.ui.cli.menu.user.tasks.add;

import cz.ucl.logic.app.entities.definition.ICategory;
import cz.ucl.logic.app.entities.definition.ITag;
import cz.ucl.ui.cli.forms.FormField;
import cz.ucl.ui.cli.menu.FormMenu;
import cz.ucl.ui.cli.menu.MenuOption;
import cz.ucl.ui.definition.forms.FormFieldType;
import cz.ucl.ui.definition.menu.IMenu;

public class AddTaskFormMenu extends FormMenu {

    private final ICategory category;

    private final ITag[] tags;

    public AddTaskFormMenu(IMenu parentMenu, String title, ICategory category, ITag[] tags) {
        super(parentMenu, "add_task", title);
        this.category = category;
        this.tags = tags;
    }

    @Override
    protected void defineForm() {
        addFormField(new FormField("title", "Název", FormFieldType.TEXTUAL));
        addFormField(new FormField("note", "Poznámka", FormFieldType.TEXTUAL));
        addFormField(new FormField("deadline", "Deadline", FormFieldType.DATETIME));
        addFormField(new FormField("isdone", "Je hotový? (ano/ne)", FormFieldType.BOOLEAN));
    }

    @Override
    protected void build() {
        setDescription("Pro přidání úkol je třeba zadat název, poznámku a informaci zda je úkol hotový: ");

        IMenu backMenu = ui.getMenuFactory().createBackMenu(this);
        IMenu fillMenu = ui.getMenuFactory().createFillFormMenu(this.getParentMenu().getParentMenu());

        addOption(new MenuOption(nextOptionNumber(), backMenu));
        addOption(new MenuOption(nextOptionNumber(), fillMenu));
    }

    public ICategory getCategory() {
        return category;
    }

    public ITag[] getTags() {
        return tags;
    }
}
