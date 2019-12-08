package cz.ucl.ui.cli.menu.user.tasks.edit;

import cz.ucl.logic.app.entities.definition.ICategory;
import cz.ucl.logic.app.entities.definition.ITag;
import cz.ucl.logic.app.entities.definition.task.ITask;
import cz.ucl.ui.cli.forms.FormField;
import cz.ucl.ui.cli.menu.FormMenu;
import cz.ucl.ui.cli.menu.Menu;
import cz.ucl.ui.cli.menu.MenuOption;
import cz.ucl.ui.definition.forms.FormFieldType;
import cz.ucl.ui.definition.menu.IMenu;

import java.time.format.DateTimeFormatter;

public class EditTaskFormMenu extends Menu {

    private final static DateTimeFormatter czechDateTimeFormat = DateTimeFormatter.ofPattern("d/M/yyyy H:mm");

    private final ICategory category;

    private final ITag[] tags;
    private final int taskId;

    public EditTaskFormMenu(IMenu parentMenu, String title, int taskId, ICategory category, ITag[] tags) {
        super(parentMenu, "edit_task", title);
        this.category = category;
        this.tags = tags;
        this.taskId = taskId;
        defineForm();
    }

    private void defineForm() {
        ITask task = logic.getTaskById(taskId);

        addFormField(new FormField("title", "Název (" + task.getTitle() + ")", FormFieldType.TEXTUAL));
        addFormField(new FormField("note", "Poznámka (" + task.getNote() + ")", FormFieldType.TEXTUAL, false));
        addFormField(new FormField("deadline", "Deadline (" + task.getDeadlineAt().format(czechDateTimeFormat), FormFieldType.DATETIME, false));
        addFormField(new FormField("isdone", "Zda je hotový? (ano/ne)", FormFieldType.BOOLEAN));
    }

    @Override
    protected void build() {
        setDescription("Pro upravení úkolu je třeba zadat název, poznámku a informaci zda je úkol hotový: ");

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

    public int getTaskId() {
        return taskId;
    }

    @Override
    public boolean isForm() {
        return true;
    }
}
