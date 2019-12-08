package cz.ucl.ui.cli.menu.user.tasks.filter;

import cz.ucl.logic.app.entities.definition.task.ITaskFilter;
import cz.ucl.logic.app.services.definition.TasksOrder;
import cz.ucl.ui.cli.menu.Menu;
import cz.ucl.ui.cli.menu.MenuOption;
import cz.ucl.ui.definition.menu.IMenu;

public class TaskOrderFilterMenu extends Menu {

    private final ITaskFilter taskFilter;

    public TaskOrderFilterMenu(IMenu parentMenu, String title, ITaskFilter taskFilter) {
        super(parentMenu, "task_filter_order", title);
        this.taskFilter = taskFilter;
    }

    @Override
    protected void build() {
        setDescription("Momentálně se řádí podle `" + taskFilter.getOrder().toString() + "`");

        for (TasksOrder order : TasksOrder.values()) {
            if (order != taskFilter.getOrder()) {
                IMenu actionMenu = ui.getMenuFactory().createTaskSetOrderFilterActionMenu(this, taskFilter, order);
                addOption(new MenuOption(nextOptionNumber(), "Řadit podle - " + order.toString(), actionMenu));
            }
        }

        IMenu backMenu = ui.getMenuFactory().createBackMenu(this);
        addOption(new MenuOption(nextOptionNumber(), backMenu));
    }
}
