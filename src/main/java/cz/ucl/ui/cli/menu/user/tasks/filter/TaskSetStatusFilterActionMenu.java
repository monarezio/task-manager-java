package cz.ucl.ui.cli.menu.user.tasks.filter;

import cz.ucl.logic.app.entities.definition.TaskDoneStatus;
import cz.ucl.logic.app.entities.definition.task.ITaskFilter;
import cz.ucl.ui.definition.menu.ActionMenu;
import cz.ucl.ui.definition.menu.IMenu;

public class TaskSetStatusFilterActionMenu extends ActionMenu {

    private final ITaskFilter taskFilter;
    private final TaskDoneStatus status;

    public TaskSetStatusFilterActionMenu(IMenu parentMenu, ITaskFilter taskFilter, TaskDoneStatus status) {
        super(parentMenu, "task_filter_set_status", "", parentMenu.getParentMenu());
        this.taskFilter = taskFilter;
        this.status = status;
    }

    @Override
    public void action() {
        taskFilter.setTaskStatus(status);
    }
}
