package cz.ucl.logic;

import cz.ucl.logic.app.entities.definition.ITag;
import cz.ucl.logic.app.entities.definition.ITask;
import cz.ucl.logic.app.entities.definition.IUser;
import cz.ucl.logic.app.services.definition.ICategoryService;
import cz.ucl.logic.app.services.definition.ITagService;
import cz.ucl.logic.app.services.definition.ITaskService;
import cz.ucl.logic.app.services.definition.IUserService;

/**
 * Everything which is shown (or done) by the AppLogic, has to be related only to the logged in user
 */
public interface IAppLogic extends ICategoryService, IUserService, ITagService, ITaskService {
    //region Mock Data
    /** Generates mock (testing) data */
    void generateMockData();
    //endregion
}
