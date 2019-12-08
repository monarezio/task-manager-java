package cz.ucl.ui.cli.menu.user.tasks;

import cz.ucl.logic.app.entities.definition.task.ITasksCollection;
import cz.ucl.logic.app.entities.task.TaskFilter;
import cz.ucl.logic.app.entities.definition.ITag;
import cz.ucl.logic.app.entities.definition.task.ITask;
import cz.ucl.logic.app.entities.definition.TaskDoneStatus;
import cz.ucl.logic.app.services.definition.TasksOrder;
import cz.ucl.ui.cli.menu.Menu;
import cz.ucl.ui.cli.menu.MenuOption;
import cz.ucl.ui.definition.menu.IMenu;

public class TasksListMenu extends Menu {

    private final TaskFilter taskFilter;

    private TasksListMenu(IMenu parentMenu, String title, TaskFilter taskFilter) {
        super(parentMenu, "all_tasks_menu", title);
        this.taskFilter = taskFilter;
    }

    public TasksListMenu(IMenu parentMenu, String title) {
        this(parentMenu, title, new TaskFilter(TasksOrder.BY_DEADLINE_AT_ASC, "", null, new ITag[0], 1, TaskDoneStatus.IGNORE));
    }

    @Override
    protected void build() {
        ITasksCollection tasksCollection = logic.getAllTasks(taskFilter);

        StringBuilder sb = new StringBuilder("Seznam všech uživatelských úkolů.");
        sb.append("\nStránka ");
        sb.append(tasksCollection.getCurrentPage());
        sb.append("/");
        sb.append(tasksCollection.amountOfPages());

        if (tasksCollection.getTasks().length == 0)
            sb.append("\n\nSeznam úkolů je prázdný");

        setDescription(sb.toString());

        for (ITask task : tasksCollection.getTasks()) {
            IMenu taskDetail = ui.getMenuFactory().createTaskDetail(this, (int) task.getId());
            addOption(new MenuOption(nextOptionNumber(), "Detail - " + task.getTitle(), taskDetail));
        }

        IMenu backMenu = ui.getMenuFactory().createBackMenu(this);
        addOption(new MenuOption(nextOptionNumber(), backMenu));
    }

    @Override
    public void initialize() {
        clearOptions();
        build();
    }
}
