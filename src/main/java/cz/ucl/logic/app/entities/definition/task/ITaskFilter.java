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

    void setPage(int page);
    void setOrder(TasksOrder order);
    void setSearchKeyword(String searchKeyword);
    void setByCategory(ICategory category);
    void removeTag(ITag tag);
    void clearAllTags();
    void addTag(ITag tag);
    void setTaskStatus(TaskDoneStatus taskDoneStatus);

}
