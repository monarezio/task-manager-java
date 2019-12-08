package cz.ucl.logic.data.managers.definitions;

import cz.ucl.logic.app.entities.definition.ICategory;
import cz.ucl.logic.app.entities.definition.ITag;
import cz.ucl.logic.app.entities.definition.task.ITask;
import cz.ucl.logic.app.entities.definition.task.ITaskFilter;
import cz.ucl.logic.app.entities.definition.task.ITasksCollection;

import java.time.LocalDateTime;

public interface ITaskManager {

    ITasksCollection getAllByFilter(long userId, ITaskFilter filter);

    ITask get(long userId, long taskId);

    void add(long userId, String title, String note, ICategory categoryId, ITag[] tags, LocalDateTime deadline, boolean isDone);

    void update(long taskId, long userId, String title, String note, ICategory categoryId, ITag[] tags, LocalDateTime deadline, boolean isDone);

    void destroy(long taskId, long userId);
}
