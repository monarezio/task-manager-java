package cz.ucl.logic.app.entities.definition.task;

import cz.ucl.logic.app.entities.definition.ICategory;
import cz.ucl.logic.app.entities.definition.ITag;
import cz.ucl.logic.app.entities.definition.TaskDoneStatus;
import cz.ucl.logic.app.services.definition.TasksOrder;

public interface ITaskFilter {

    TasksOrder getOrder();
    String getSearchKeyword();
    ICategory getByCategory();
    ITag[] getByTags();
    int getPage();
    TaskDoneStatus getTaskDoneStatus();

    ITaskFilter setOrder(TasksOrder order);
    ITaskFilter setSearchKeyword(String searchKeyword);
    ITaskFilter setByCategory(ICategory category);
    ITaskFilter addTag(ITag tag);
    ITaskFilter removeTag(ITag tag);
    ITaskFilter clearAllTags();
    ITaskFilter setTaskStatus(TaskDoneStatus taskDoneStatus);

}
