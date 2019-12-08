package cz.ucl.ui.cli.menu.user.tasks.filter;

import cz.ucl.logic.app.entities.definition.task.ITask;
import cz.ucl.logic.app.entities.definition.task.ITaskFilter;
import cz.ucl.logic.app.services.definition.TasksOrder;
import cz.ucl.ui.definition.menu.ActionMenu;
import cz.ucl.ui.definition.menu.IMenu;

public class TaskSetOrderFilterActionMenu extends ActionMenu {

    private final ITaskFilter taskFilter;
    private final TasksOrder order;

    public TaskSetOrderFilterActionMenu(IMenu parentMenu, ITaskFilter taskFilter, TasksOrder order) {
        super(parentMenu, "task_filter_set_order", "", parentMenu.getParentMenu());
        this.taskFilter = taskFilter;
        this.order = order;
    }

    @Override
    public void action() {
        taskFilter.setOrder(order);
    }
}
