package cz.ucl.ui.cli.menu.user.tasks;

import cz.ucl.logic.app.entities.definition.task.ITaskFilter;
import cz.ucl.logic.app.entities.definition.task.ITasksCollection;
import cz.ucl.logic.app.entities.task.TaskFilter;
import cz.ucl.logic.app.entities.definition.task.ITask;
import cz.ucl.ui.cli.menu.Menu;
import cz.ucl.ui.cli.menu.MenuOption;
import cz.ucl.ui.definition.menu.IMenu;

public class TasksListMenu extends Menu {

    private final ITaskFilter taskFilter;

    public TasksListMenu(IMenu parentMenu, String title, ITaskFilter taskFilter) {
        super(parentMenu, "all_tasks_menu", title);
        this.taskFilter = taskFilter;
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

        IMenu filterMenu = ui.getMenuFactory().createTaskFilterMenu(this, taskFilter);
        addOption(new MenuOption(nextOptionNumber(), filterMenu));

        if (taskFilter.getPage() < tasksCollection.amountOfPages()) {
            IMenu upMenu = ui.getMenuFactory().createTaskPageUpAction(this, taskFilter);
            addOption(new MenuOption(nextOptionNumber(), upMenu));
        }

        if (taskFilter.getPage() > 1) {
            IMenu downMenu = ui.getMenuFactory().createTaskPageDownAction(this, taskFilter);
            addOption(new MenuOption(nextOptionNumber(), downMenu));
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
