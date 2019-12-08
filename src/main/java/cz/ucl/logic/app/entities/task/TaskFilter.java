package cz.ucl.logic.app.entities.task;

import cz.ucl.logic.app.entities.definition.ICategory;
import cz.ucl.logic.app.entities.definition.ITag;
import cz.ucl.logic.app.entities.definition.task.ITaskFilter;
import cz.ucl.logic.app.entities.definition.TaskDoneStatus;
import cz.ucl.logic.app.services.definition.TasksOrder;

import java.util.*;

public class TaskFilter implements ITaskFilter {

    private TasksOrder order;
    private String searchKeyword;
    private ICategory byCategory;
    private Set<ITag> byTags;
    private int page;
    private TaskDoneStatus taskDoneStatus;

    public TaskFilter(TasksOrder order, String searchKeyword, ICategory byCategory, ITag[] byTags, int page, TaskDoneStatus taskDoneStatus) {
        this.order = order;
        this.searchKeyword = searchKeyword;
        this.byCategory = byCategory;
        this.byTags = new HashSet<>(Arrays.asList(byTags));
        this.page = page;
        this.taskDoneStatus = taskDoneStatus;
    }

    public TaskFilter() {
        this(TasksOrder.BY_DEADLINE_AT_ASC, "", null, new ITag[0], 1, TaskDoneStatus.IGNORE);
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
    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public void setOrder(TasksOrder order) {
        setPage(1);
        this.order = order;
    }

    @Override
    public void setSearchKeyword(String searchKeyword) {
        setPage(1);
        this.searchKeyword = searchKeyword;
    }

    @Override
    public void setByCategory(ICategory byCategory) {
        setPage(1);
        this.byCategory = byCategory;
    }

    @Override
    public void setTaskStatus(TaskDoneStatus taskDoneStatus) {
        setPage(1);
        this.taskDoneStatus = taskDoneStatus;
    }

    @Override
    public void removeTag(ITag tag) {
        setPage(1);
        byTags.remove(tag);
    }

    @Override
    public void clearAllTags() {
        setPage(1);
        byTags.clear();
    }

    @Override
    public void addTag(ITag tag) {
        setPage(1);
        byTags.add(tag);
    }
}
