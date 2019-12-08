package cz.ucl.ui.cli.menu.user.tasks.filter;

import cz.ucl.logic.app.entities.definition.ITag;
import cz.ucl.logic.app.entities.definition.task.ITaskFilter;
import cz.ucl.ui.cli.menu.Menu;
import cz.ucl.ui.cli.menu.MenuOption;
import cz.ucl.ui.definition.menu.IMenu;

import java.util.Arrays;
import java.util.List;

public class TaskTagFilterMenu extends Menu {

    private final ITaskFilter taskFilter;

    private ITag tagToRemove = null; //TODO: Fuj fuj
    private ITag tagToAdd = null; //TODO: Fuj fuj

    public TaskTagFilterMenu(IMenu parentMenu, String title, ITaskFilter taskFilter) {
        super(parentMenu, "task_filter_tag", title);
        this.taskFilter = taskFilter;
    }

    private TaskTagFilterMenu(IMenu parentMenu, ITaskFilter taskFilter, ITag tagToAdd, ITag tagToRemove) { //TODO: Fuj fuj
        this(parentMenu, "", taskFilter);

        this.tagToAdd = tagToAdd;
        this.tagToRemove = tagToRemove;
    }

    @Override
    protected void build() {
        if(tagToAdd != null) //TODO: Fuj fuj
            taskFilter.addTag(tagToAdd);
        else if(tagToRemove != null) //TODO: Fuj fuj
            taskFilter.removeTag(tagToRemove);

        ITag[] tags = logic.getAllTags();

        setDescription("Přidat/Odebrat tagy podle kterých chcete filtrovat");

        List<ITag> filterTags = Arrays.asList(taskFilter.getByTags());

        for (ITag tag : tags) {
            if(filterTags.contains(tag)) {
                IMenu menu = new TaskTagFilterMenu(this.getParentMenu(), taskFilter, null, tag);
                addOption(new MenuOption(nextOptionNumber(), "Odebrat - " + tag.getTitle(), menu));
            } else {
                IMenu menu = new TaskTagFilterMenu(this.getParentMenu(), taskFilter, tag, null);
                addOption(new MenuOption(nextOptionNumber(), "Přidat - " + tag.getTitle(), menu));
            }
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
