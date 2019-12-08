package cz.ucl.ui.cli.menu.user.tasks.filter;

import cz.ucl.logic.app.entities.definition.ICategory;
import cz.ucl.logic.app.entities.definition.task.ITaskFilter;
import cz.ucl.ui.definition.menu.ActionMenu;
import cz.ucl.ui.definition.menu.IMenu;

public class TaskSetCategoryFilterActionMenu extends ActionMenu {

    private final ITaskFilter taskFilter;
    private final ICategory category;

    public TaskSetCategoryFilterActionMenu(IMenu parentMenu, ITaskFilter taskFilter, ICategory category) {
        super(parentMenu, "task_filter_set_category", "", parentMenu.getParentMenu());
        this.taskFilter = taskFilter;
        this.category = category;
    }

    @Override
    public void action() {
        taskFilter.setByCategory(category);
    }
}
