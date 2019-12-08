package cz.ucl.ui.cli.menu.user.tasks.filter;

import cz.ucl.logic.app.entities.definition.task.ITaskFilter;
import cz.ucl.ui.cli.forms.FormField;
import cz.ucl.ui.cli.menu.FormMenu;
import cz.ucl.ui.cli.menu.MenuOption;
import cz.ucl.ui.definition.forms.FormFieldType;
import cz.ucl.ui.definition.menu.IMenu;

public class TaskSearchFilterMenu extends FormMenu {

    private final ITaskFilter taskFilter;

    public TaskSearchFilterMenu(IMenu parentMenu, String title, ITaskFilter taskFilter) {
        super(parentMenu, "task_search_filter", title);
        this.taskFilter = taskFilter;
    }

    protected void defineForm() {
        addFormField(new FormField("search", "Vyhledat podle", FormFieldType.TEXTUAL, false));
    }

    @Override
    protected void build() {
        setDescription("Vyplněním formuláře nastavíte text podle kterého s ebude vyhledávat: ");

        IMenu backMenu = ui.getMenuFactory().createBackMenu(this);
        IMenu fillMenu = ui.getMenuFactory().createFillFormMenu(this);

        addOption(new MenuOption(nextOptionNumber(), backMenu));
        addOption(new MenuOption(nextOptionNumber(), fillMenu));
    }

    public ITaskFilter getTaskFilter() {
        return taskFilter;
    }
}
