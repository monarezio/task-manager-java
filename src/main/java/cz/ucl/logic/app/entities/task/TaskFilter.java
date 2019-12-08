package cz.ucl.logic.app.entities.task;

import cz.ucl.logic.app.entities.definition.ICategory;
import cz.ucl.logic.app.entities.definition.ITag;
import cz.ucl.logic.app.entities.definition.task.ITaskFilter;
import cz.ucl.logic.app.entities.definition.TaskDoneStatus;
import cz.ucl.logic.app.services.definition.TasksOrder;

import java.util.*;

public class TaskFilter implements ITaskFilter {

    private final TasksOrder order;
    private final String searchKeyword;
    private final ICategory byCategory;
    private final Set<ITag> byTags;
    private final int page;
    private final TaskDoneStatus taskDoneStatus;

    public TaskFilter(TasksOrder order, String searchKeyword, ICategory byCategory, ITag[] byTags, int page, TaskDoneStatus taskDoneStatus) {
        this.order = order;
        this.searchKeyword = searchKeyword;
        this.byCategory = byCategory;
        this.byTags = new HashSet<>(Arrays.asList(byTags));
        this.page = page;
        this.taskDoneStatus = taskDoneStatus;
    }

    @Override
    public TasksOrder getOrder() {
        return order;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    @Override
    public ICategory getByCategory() {
        return byCategory;
    }

    @Override
    public ITag[] getByTags() {
        return byTags.toArray(new ITag[0]);
    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public TaskDoneStatus getTaskDoneStatus() {
        return taskDoneStatus;
    }

    @Override
    public ITaskFilter setOrder(TasksOrder tasksOrder) {
        return new TaskFilter(tasksOrder, searchKeyword, byCategory, byTags.toArray(new ITag[0]), page, taskDoneStatus);
    }

    @Override
    public ITaskFilter setSearchKeyword(String searchKeyword) {
        return new TaskFilter(order, searchKeyword, byCategory, byTags.toArray(new ITag[0]), page, taskDoneStatus);
    }

    @Override
    public ITaskFilter setByCategory(ICategory category) {
        return new TaskFilter(order, searchKeyword, category, byTags.toArray(new ITag[0]), page, taskDoneStatus);
    }

    @Override
    public ITaskFilter addTag(ITag tag) {
        byTags.add(tag);
        return new TaskFilter(order, searchKeyword, byCategory, byTags.toArray(new ITag[0]), page, taskDoneStatus);
    }

    @Override
    public ITaskFilter removeTag(ITag tag) {
        byTags.remove(tag);
        return new TaskFilter(order, searchKeyword, byCategory, byTags.toArray(new ITag[0]), page, taskDoneStatus);
    }

    @Override
    public TaskFilter clearAllTags() {
        return new TaskFilter(order, searchKeyword, byCategory, new ITag[0], page, taskDoneStatus);
    }

    @Override
    public ITaskFilter setTaskStatus(TaskDoneStatus taskDoneStatus) {
        return new TaskFilter(order, searchKeyword, byCategory, byTags.toArray(new ITag[0]), page, taskDoneStatus);
    }
}
