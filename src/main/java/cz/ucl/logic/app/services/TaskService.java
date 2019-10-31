package cz.ucl.logic.app.services;

import cz.ucl.logic.app.entities.definition.Color;
import cz.ucl.logic.app.entities.definition.ICategory;
import cz.ucl.logic.app.entities.definition.ITag;
import cz.ucl.logic.app.entities.definition.ITask;
import cz.ucl.logic.app.services.definition.ITaskService;
import cz.ucl.logic.app.services.definition.IUserService;
import cz.ucl.logic.app.services.definition.TasksOrder;

// TODO: Implement this
public class TaskService implements ITaskService {

    private final IUserService userService;

    public TaskService(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public ITask[] getAllTasks() {
        return new ITask[0];
    }

    @Override
    public ITask[] getAllTasks(TasksOrder order) {
        return new ITask[0];
    }

    @Override
    public ITask[] searchTasksForKeyword(String keyword) {
        return new ITask[0];
    }

    @Override
    public ITask[] getAllTasksByCategory(ICategory category) {
        return new ITask[0];
    }

    @Override
    public ITask[] getAllTasksByTag(ITag tag) {
        return new ITask[0];
    }

    @Override
    public ITask[] getAllTasksByTags(ITag[] tag) {
        return new ITask[0];
    }

    @Override
    public ITask[] getAllTasksByTags(ITag[] tag, ICategory category) {
        return new ITask[0];
    }

    @Override
    public ITask getTaskById(int id) {
        return null;
    }

    @Override
    public void createTask(String title) {

    }

    @Override
    public void createTask(String title, String note) {

    }

    @Override
    public void createTask(String title, String note, ICategory category) {

    }

    @Override
    public void updateTask(int id, String title, Color color) {

    }

    @Override
    public void destroyTask(int id) {

    }
}
