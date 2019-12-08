package cz.ucl.ui.cli.menu.user.tasks.filter;

import cz.ucl.logic.app.entities.Category;
import cz.ucl.logic.app.entities.definition.Color;
import cz.ucl.logic.app.entities.definition.ICategory;
import cz.ucl.logic.app.entities.definition.task.ITask;
import cz.ucl.logic.app.entities.definition.task.ITaskFilter;
import cz.ucl.ui.cli.menu.Menu;
import cz.ucl.ui.cli.menu.MenuOption;
import cz.ucl.ui.definition.menu.IMenu;

public class TaskCategoryFilterMenu extends Menu {

    private final ITaskFilter taskFilter;

    public TaskCategoryFilterMenu(IMenu parentMenu, String title, ITaskFilter taskFilter) {
        super(parentMenu, "task_category_filter", title);
        this.taskFilter = taskFilter;
    }

    @Override
    protected void build() {
        ICategory[] categories = logic.getAllCategories();
        setDescription("Vybrat kategorii pro filtraci, momentálně se filtruje podle `" + (taskFilter.getByCategory() != null ? taskFilter.getByCategory().getTitle() : "Žádné") + "`");

        for (ICategory category : categories) {
            if(taskFilter.getByCategory() == null || category.getId() != taskFilter.getByCategory().getId()) {
                IMenu actionMenu = ui.getMenuFactory().createTaskSetCategoryFilterActionMenu(this.getParentMenu(), taskFilter, category);
                addOption(new MenuOption(nextOptionNumber(), category.getTitle(), actionMenu));
            }
        }
        IMenu actionUncategorizedMenu = ui.getMenuFactory().createTaskSetCategoryFilterActionMenu(this.getParentMenu(), taskFilter, new Category(-1, null, "Uncategorized", Color.RED, new ITask[0]));
        addOption(new MenuOption(nextOptionNumber(), "Uncategorized", actionUncategorizedMenu));

        IMenu actionNoFilterMenu = ui.getMenuFactory().createTaskSetCategoryFilterActionMenu(this.getParentMenu(), taskFilter, null);
        addOption(new MenuOption(nextOptionNumber(), "Nefiltrovat podle kategorie", actionNoFilterMenu));

        IMenu backMenu = ui.getMenuFactory().createBackMenu(this);
        addOption(new MenuOption(nextOptionNumber(), backMenu));
    }

}
