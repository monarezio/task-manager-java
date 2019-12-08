package cz.ucl.ui.cli.menu.user.tasks.edit;

import cz.ucl.logic.app.entities.definition.ICategory;
import cz.ucl.logic.app.entities.definition.ITag;
import cz.ucl.logic.app.entities.definition.task.ITask;
import cz.ucl.ui.cli.menu.Menu;
import cz.ucl.ui.cli.menu.MenuOption;
import cz.ucl.ui.definition.menu.IMenu;

public class EditTaskCategoryMenu extends Menu {

    private final int taskId;

    public EditTaskCategoryMenu(IMenu parentMenu, int taskId, String title) {
        super(parentMenu, "edit_task_category_menu", title);
        this.taskId = taskId;
    }

    @Override
    protected void build() {
        ICategory[] categories = logic.getAllCategories();
        ITask task = logic.getTaskById(taskId);

        setDescription("Vyberte si kategorii, pod kterou se bude nacházet upravený úkol.\nMomentálně je nastavená `" + task.getCategory().getTitle() + "` kategorie\n");

        for (ICategory category : categories) {
            IMenu editTaskTagMenu = ui.getMenuFactory().createEditTaskTagMenu(this, category, task.getTags(), taskId);
            addOption(new MenuOption(nextOptionNumber(), category.getTitle(), editTaskTagMenu));
        }

        IMenu editTaskTag = ui.getMenuFactory().createEditTaskTagMenu(this, null, task.getTags(), taskId);
        addOption(new MenuOption(nextOptionNumber(), "Žádná kategorie", editTaskTag));

        IMenu cancel = ui.getMenuFactory().createBackMenu(this);
        addOption(new MenuOption(nextOptionNumber(), "Zrušit editaci", cancel));
    }
}
