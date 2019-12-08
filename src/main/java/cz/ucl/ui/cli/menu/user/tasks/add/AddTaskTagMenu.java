package cz.ucl.ui.cli.menu.user.tasks.add;

import cz.ucl.logic.app.entities.definition.ICategory;
import cz.ucl.logic.app.entities.definition.ITag;
import cz.ucl.ui.cli.menu.Menu;
import cz.ucl.ui.cli.menu.MenuOption;
import cz.ucl.ui.definition.menu.IMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddTaskTagMenu extends Menu {

    private final ICategory category;
    private final List<ITag> selectedTags;

    public AddTaskTagMenu(IMenu parentMenu, String title, ICategory category, ITag[] tags) {
        super(parentMenu, "add_task_category_menu", title);
        this.category = category;
        this.selectedTags = Arrays.asList(tags);
    }

    @Override
    protected void build() {
        ITag[] tags = logic.getAllTags();

        setDescription("Vyberte si tagy, pod kterýma se bude nacházet nově vytvořený úkol.\n");

        for (ITag tag : tags) {
            List<ITag> newTags = new ArrayList<>(selectedTags);
            if (!selectedTags.contains(tag)) {
                newTags.add(tag);
                IMenu addTaskInfoFormMenu = ui.getMenuFactory()
                        .createAddTaskTagMenu(this.getParentMenu(), category, newTags.toArray(new ITag[0]));
                addOption(new MenuOption(nextOptionNumber(), "Přidat tag - " + tag.getTitle(), addTaskInfoFormMenu));
            } else {
                newTags.remove(tag);
                IMenu addTaskInfoFormMenu = ui.getMenuFactory()
                        .createAddTaskTagMenu(this.getParentMenu(), category, newTags.toArray(new ITag[0]));
                addOption(new MenuOption(nextOptionNumber(), "Odebrat tag - " + tag.getTitle(), addTaskInfoFormMenu));
            }
        }

        IMenu addTaskFromMenu = ui.getMenuFactory()
                .createAddTaskBasicInfoFormMenu(this.getParentMenu().getParentMenu(), category, selectedTags.toArray(new ITag[0]));
        addOption(new MenuOption(nextOptionNumber(), "Pokračovat na formulář", addTaskFromMenu));

        IMenu back = ui.getMenuFactory().createBackMenu(this);
        addOption(new MenuOption(nextOptionNumber(), "Zpět na výběr kategorie", back));

        IMenu cancel = ui.getMenuFactory().createBackMenu(this.getParentMenu());
        addOption(new MenuOption(nextOptionNumber(), "Zrušit přidání", cancel));
    }
}
