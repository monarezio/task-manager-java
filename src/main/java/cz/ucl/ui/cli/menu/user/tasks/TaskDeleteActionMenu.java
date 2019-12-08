package cz.ucl.ui.cli.menu.user.tasks;

import cz.ucl.ui.definition.menu.ActionMenu;
import cz.ucl.ui.definition.menu.IMenu;

public class TaskDeleteActionMenu extends ActionMenu {

    private final int taskId;

    public TaskDeleteActionMenu(IMenu parentMenu, String title, int taskId) {
        super(parentMenu, "task_delete", title, parentMenu.getParentMenu());
        this.taskId = taskId;
    }

    @Override
    public void action() {
        logic.destroyTask(taskId);
    }
}
