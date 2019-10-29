package cz.ucl.logic;

import cz.ucl.logic.app.entities.definition.ITag;
import cz.ucl.logic.app.entities.definition.ITask;
import cz.ucl.logic.app.entities.definition.IUser;
import cz.ucl.logic.app.services.definition.ICategoryService;
import cz.ucl.logic.app.services.definition.IUserService;

/**
 * Everything which is shown (or done) by the AppLogic, has to be related only to the logged in user
 */
public interface IAppLogic extends ICategoryService, IUserService {
    //region Tasks
    /** Returns one task by its ID */
    ITask getTaskById(int id);

    /** Returns all tasks without any filter by category or tags */
    ITask[] getAllTasks();

    /** Returns tasks filtered by given criteria */
    ITask[] getTasksFilteredByCategory(String categoryTitle);
    ITask[] getTasksFilteredByTags(String[] tagTitles);
    ITask[] getTasksFilteredByCategoryAndTags(String categoryTitle, String[] tagTitles);
    //endregion

    //region Tags
    /** Returns all tags */
    ITag[] getAllTags();
    //endregion

    //region Mock Data
    /** Generates mock (testing) data */
    void generateMockData();
    //endregion
}
