package cz.ucl.ui.cli.menu.user.tasks;

import cz.ucl.logic.app.entities.definition.task.ITask;
import cz.ucl.ui.cli.menu.Menu;
import cz.ucl.ui.cli.menu.MenuOption;
import cz.ucl.ui.definition.menu.IMenu;

public class TaskDetailMenu extends Menu {

    private final int taskId;

    public TaskDetailMenu(IMenu parentMenu, String title, int taskId) {
        super(parentMenu, "task_detail", title);
        this.taskId = taskId;
    }

    @Override
    protected void build() {
        ITask task = logic.getTaskById(taskId);

        setDescription(ui.getTaskView().formatTask(task));

        IMenu editMenu = ui.getMenuFactory().createEditTaskCategoryMenu(this, taskId);
        addOption(new MenuOption(nextOptionNumber(), editMenu));

        IMenu deleteMenu = ui.getMenuFactory().createDeleteTaskMenu(this, taskId);
        addOption(new MenuOption(nextOptionNumber(), deleteMenu));

        IMenu backMenu = ui.getMenuFactory().createBackMenu(this);
        addOption(new MenuOption(nextOptionNumber(), backMenu));
    }

    @Override
    public void initialize() {
        clearOptions();
        build();
    }
}
