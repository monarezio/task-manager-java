package cz.ucl.logic.app.services.definition;

import cz.ucl.logic.app.entities.definition.*;
import cz.ucl.logic.app.entities.definition.task.ITask;
import cz.ucl.logic.app.entities.definition.task.ITaskFilter;
import cz.ucl.logic.app.entities.definition.task.ITasksCollection;
import cz.ucl.logic.app.entities.task.TasksCollection;

import java.time.LocalDateTime;

/**
 * This interface describes a class which should contain all application logic related to tasks
 * management and manipulation
 */
public interface ITaskService {

    ITasksCollection getAllTasks(ITaskFilter taskFilter);

    /** Returns one task by its ID */
    ITask getTaskById(int id);

    /** Creates a task with title and is done */
    void createTask(String title, boolean isDone);

    /** Creates a task with title, note and and is done*/
    void createTask(String title, String note, boolean isDone);

    /** Creates a task with title, note, category and is done */
    void createTask(String title, String note, ICategory category, boolean isDone);

    /** Creates a task with title, note, category, tags, deadline and is done */
    void createTask(String title, String note, ICategory category, ITag[] tags, LocalDateTime deadline, boolean isDone);

    void updateTask(int id, String title, String note, ICategory category, ITag[] tags, LocalDateTime deadline, boolean isDone);

    /** Finds a task by its ID and destroys it */
    void destroyTask(int id);
}
