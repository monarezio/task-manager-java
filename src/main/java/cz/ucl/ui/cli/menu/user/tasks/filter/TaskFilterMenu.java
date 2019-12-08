package cz.ucl.ui.cli.menu.user.tasks.filter;

import cz.ucl.logic.app.entities.definition.task.ITaskFilter;
import cz.ucl.ui.cli.menu.Menu;
import cz.ucl.ui.cli.menu.MenuOption;
import cz.ucl.ui.definition.menu.IMenu;

public class TaskFilterMenu extends Menu {

    private final ITaskFilter taskFilter;

    public TaskFilterMenu(IMenu parentMenu, String title, ITaskFilter taskFilter) {
        super(parentMenu, "task_filter", title);
        this.taskFilter = taskFilter;
    }

    @Override
    protected void build() {
        setDescription(ui.getFilterMenuView().formatTaskFilter(taskFilter));

        IMenu filterMenu = ui.getMenuFactory().createTaskSearchFilterMenu(this, taskFilter);
        addOption(new MenuOption(nextOptionNumber(), filterMenu));

        IMenu tagMenu = ui.getMenuFactory().createTaskTagFilterMenu(this, taskFilter);
        addOption(new MenuOption(nextOptionNumber(), tagMenu));

        IMenu categoryMenu = ui.getMenuFactory().createTaskCategoryFilterMenu(this, taskFilter);
        addOption(new MenuOption(nextOptionNumber(), categoryMenu));

        IMenu orderMenu = ui.getMenuFactory().createTaskOrderFilterMenu(this, taskFilter);
        addOption(new MenuOption(nextOptionNumber(), orderMenu));

        IMenu statusMenu = ui.getMenuFactory().createTaskDoneStatusFilterMenu(this, taskFilter);
        addOption(new MenuOption(nextOptionNumber(), statusMenu));

        IMenu backMenu = ui.getMenuFactory().createBackMenu(this);
        addOption(new MenuOption(nextOptionNumber(), backMenu));
    }

    @Override
    public void initialize() {
        clearOptions();
        build();
    }
}
