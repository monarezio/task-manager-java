package cz.ucl.ui.cli.menu.user.tasks.pagination;

import cz.ucl.logic.app.entities.definition.task.ITaskFilter;
import cz.ucl.ui.definition.menu.ActionMenu;
import cz.ucl.ui.definition.menu.IMenu;

public class TaskPageDownActionMenu extends ActionMenu {

    private final ITaskFilter taskFilter;

    public TaskPageDownActionMenu(IMenu parentMenu, String title, ITaskFilter taskFilter) {
        super(parentMenu, "task_page_down", title, parentMenu);
        this.taskFilter = taskFilter;
    }

    @Override
    public void action() {
        taskFilter.setPage(taskFilter.getPage() - 1);
    }
}
