package cz.ucl.logic.app.entities.definition.task;

public interface ITasksCollection {

    int getCurrentPage();
    int amountOfPages();
    ITask[] getTasks();

}
