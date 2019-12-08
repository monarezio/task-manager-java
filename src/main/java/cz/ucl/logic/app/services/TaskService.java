package cz.ucl.logic.app.services;

import cz.ucl.logic.app.entities.definition.*;
import cz.ucl.logic.app.entities.definition.task.ITask;
import cz.ucl.logic.app.entities.definition.task.ITaskFilter;
import cz.ucl.logic.app.entities.definition.task.ITasksCollection;
import cz.ucl.logic.app.entities.task.TasksCollection;
import cz.ucl.logic.app.services.definition.ITaskService;
import cz.ucl.logic.app.services.definition.IUserService;
import cz.ucl.logic.app.services.definition.TasksOrder;
import cz.ucl.logic.data.managers.definitions.ITaskManager;

import java.time.LocalDateTime;

// TODO: Implement this
public class TaskService implements ITaskService {

    private final IUserService userService;
    private final ITaskManager taskManager;

    public TaskService(IUserService userService, ITaskManager taskManager) {
        this.taskManager = taskManager;
        this.userService = userService;
    }

    @Override
    public ITasksCollection getAllTasks(ITaskFilter taskFilter) {
        return taskManager.getAllByFilter(userService.getUserLoggedIn().getId(), taskFilter);
    }

    @Override
    public ITask getTaskById(int id) {
        return taskManager.get(userService.getUserLoggedIn().getId(), id);
    }

    @Override
    public void createTask(String title, boolean isDone) {
        createTask(title, "", null, new ITag[0], null, isDone);
    }

    @Override
    public void createTask(String title, String note, boolean isDone) {
        createTask(title, note, null, new ITag[0], null, isDone);
    }

    @Override
    public void createTask(String title, String note, ICategory category, boolean isDone) {
        createTask(title, note, category, new ITag[0], null, isDone);
    }

    @Override
    public void createTask(String title, String note, ICategory category, ITag[] tags, LocalDateTime deadline, boolean isDone) {
        taskManager.add(userService.getUserLoggedIn().getId(), title, note, category, tags, deadline, isDone);
    }

    @Override
    public void updateTask(int id, String title, String note, ICategory category, ITag[] tags, LocalDateTime deadline, boolean isDone) {
        taskManager.update(id, userService.getUserLoggedIn().getId(), title, note, category, tags, deadline, isDone);
    }

    @Override
    public void destroyTask(int id) {
        taskManager.destroy(id, userService.getUserLoggedIn().getId());
    }
}
