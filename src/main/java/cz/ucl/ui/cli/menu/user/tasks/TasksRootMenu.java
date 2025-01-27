package cz.ucl.ui.cli.menu.user.tasks;

import cz.ucl.ui.cli.menu.Menu;
import cz.ucl.ui.cli.menu.MenuOption;
import cz.ucl.ui.definition.menu.IMenu;

public class TasksRootMenu extends Menu {
    public TasksRootMenu(IMenu parentMenu, String title) {
        super(parentMenu, "tasks_menu", title);
    }

    @Override
    protected void build() {
        setDescription("Administrace tasků");

        IMenu tasksListMenu = ui.getMenuFactory().createTasksListMenu(this);
        IMenu tasksFinishedListMenu = ui.getMenuFactory().createFinishedTasksListMenu(this);
        IMenu tasksNotFinishedListMenu = ui.getMenuFactory().createNotFinishedTasksListMenu(this);
        IMenu addTaskForm = ui.getMenuFactory().createAddTaskCategoryMenu(this);

        IMenu backMenu = ui.getMenuFactory().createBackMenu(this);

        addOption(new MenuOption(nextOptionNumber(), tasksListMenu));
        addOption(new MenuOption(nextOptionNumber(), tasksFinishedListMenu));
        addOption(new MenuOption(nextOptionNumber(), tasksNotFinishedListMenu));
        addOption(new MenuOption(nextOptionNumber(), addTaskForm));
        addOption(new MenuOption(nextOptionNumber(), backMenu));
    }
}
