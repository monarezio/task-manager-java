package cz.ucl.ui.cli.menu.user.tasks.edit;

import cz.ucl.logic.app.entities.definition.ICategory;
import cz.ucl.logic.app.entities.definition.ITag;
import cz.ucl.ui.cli.menu.Menu;
import cz.ucl.ui.cli.menu.MenuOption;
import cz.ucl.ui.definition.menu.IMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EditTaskTagMenu extends Menu {

    private final ICategory category;
    private final List<ITag> selectedTags;
    private final int taskId;

    public EditTaskTagMenu(IMenu parentMenu, String title, ICategory category, ITag[] tags, int taskId) {
        super(parentMenu, "edit_task_category_menu", title);
        this.category = category;
        this.selectedTags = Arrays.asList(tags);
        this.taskId = taskId;
    }

    @Override
    protected void build() {
        ITag[] tags = logic.getAllTags(); //TODO: Finish this

        setDescription("Vyberte si tagy, pod kterýma se bude nacházet upravený úkol.\n");

        for (ITag tag : tags) {
            List<ITag> newTags = new ArrayList<>(selectedTags);
            if (!selectedTags.contains(tag)) {
                newTags.add(tag);
                IMenu editTaskInfoFormMenu = ui.getMenuFactory()
                        .createEditTaskTagMenu(this.getParentMenu(), category, newTags.toArray(new ITag[0]), taskId);
                addOption(new MenuOption(nextOptionNumber(), "Přidat tag - " + tag.getTitle(), editTaskInfoFormMenu));
            } else {
                newTags.remove(tag);
                IMenu editTaskInfoFormMenu = ui.getMenuFactory()
                        .createEditTaskTagMenu(this.getParentMenu(), category, newTags.toArray(new ITag[0]), taskId);
                addOption(new MenuOption(nextOptionNumber(), "Odebrat tag - " + tag.getTitle(), editTaskInfoFormMenu));
            }
        }

        IMenu editTaskFromMenu = ui.getMenuFactory()
                .createEditTaskBasicInfoFormMenu(this.getParentMenu().getParentMenu(), category, selectedTags.toArray(new ITag[0]), taskId);
        addOption(new MenuOption(nextOptionNumber(), "Pokračovat na formulář", editTaskFromMenu));

        IMenu back = ui.getMenuFactory().createBackMenu(this);
        addOption(new MenuOption(nextOptionNumber(), "Zpět na výběr kategorie", back));

        IMenu cancel = ui.getMenuFactory().createBackMenu(this.getParentMenu());
        addOption(new MenuOption(nextOptionNumber(), "Zrušit přidání", cancel));
    }
}
