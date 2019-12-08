package cz.ucl.logic.app.entities.task;

import cz.ucl.logic.app.entities.definition.task.ITask;
import cz.ucl.logic.app.entities.definition.task.ITasksCollection;

public class TasksCollection implements ITasksCollection {

    private final int currentPage;
    private final int amountOfPages;
    private final ITask[] tasks;

    public TasksCollection(int currentPage, int amountOfPages, ITask[] tasks) {
        this.currentPage = currentPage;
        this.amountOfPages = amountOfPages;
        this.tasks = tasks;
    }

    @Override
    public int getCurrentPage() {
        return currentPage;
    }

    @Override
    public int amountOfPages() {
        return amountOfPages;
    }

    @Override
    public ITask[] getTasks() {
        return tasks;
    }
}
