package cz.ucl.logic.app.entities.definition;

import cz.ucl.logic.app.entities.definition.task.ITask;

public interface ITaskOwner {
    ITask[] getTasks();
    ITask getTask(int i);
    void saveTask(int i, ITask task);
    void addTask(ITask task);
    int tasksCount();
}
