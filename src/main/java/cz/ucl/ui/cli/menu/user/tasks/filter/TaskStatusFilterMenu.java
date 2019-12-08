package cz.ucl.ui.cli.menu.user.tasks.filter;

import cz.ucl.logic.app.entities.definition.TaskDoneStatus;
import cz.ucl.logic.app.entities.definition.task.ITaskFilter;
import cz.ucl.ui.cli.menu.Menu;
import cz.ucl.ui.cli.menu.MenuOption;
import cz.ucl.ui.definition.menu.IMenu;

public class TaskStatusFilterMenu extends Menu {

    private final ITaskFilter taskFilter;

    public TaskStatusFilterMenu(IMenu parentMenu, String title, ITaskFilter taskFilter) {
        super(parentMenu, "task_status_filter", title);
        this.taskFilter = taskFilter;
    }

    @Override
    protected void build() {
        setDescription("Nastavit filtrační pravidlo pro stav úkolu");

        for (TaskDoneStatus status : TaskDoneStatus.values()) {
            IMenu actionMenu = ui.getMenuFactory().createTaskSetStatusFilterActionMenu(this, taskFilter, status);
            addOption(new MenuOption(nextOptionNumber(), "Filtrovat pouze - " + status.toString(), actionMenu));
        }

        IMenu backMenu = ui.getMenuFactory().createBackMenu(this);
        addOption(new MenuOption(nextOptionNumber(), backMenu));
    }
}
