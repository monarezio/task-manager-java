package cz.ucl.ui.cli.menu.user.tasks.add;

import cz.ucl.logic.app.entities.definition.ICategory;
import cz.ucl.logic.app.entities.definition.ITag;
import cz.ucl.ui.cli.menu.FormMenu;
import cz.ucl.ui.cli.menu.Menu;
import cz.ucl.ui.cli.menu.MenuOption;
import cz.ucl.ui.definition.menu.IMenu;

public class AddTaskCategoryMenu extends Menu {
    public AddTaskCategoryMenu(IMenu parentMenu, String title) {
        super(parentMenu, "add_task_category_menu", title);
    }

    @Override
    protected void build() {
        ICategory[] categories = logic.getAllCategories();

        setDescription("Vyberte si kategorii, pod kterou se bude nacházet nově vytvořený úkol.\n");

        for (ICategory category : categories) {
            IMenu addTaskTagMenu = ui.getMenuFactory().createAddTaskTagMenu(this, category, new ITag[0]);
            addOption(new MenuOption(nextOptionNumber(), category.getTitle(), addTaskTagMenu));
        }

        IMenu addTaskTag = ui.getMenuFactory().createAddTaskTagMenu(this, null, new ITag[0]);
        addOption(new MenuOption(nextOptionNumber(), "Žádná kategorie", addTaskTag));

        IMenu cancel = ui.getMenuFactory().createBackMenu(this);
        addOption(new MenuOption(nextOptionNumber(), "Zrušit přidání", cancel));
    }
}
