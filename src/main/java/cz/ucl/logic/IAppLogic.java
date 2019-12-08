package cz.ucl.logic;

import cz.ucl.logic.app.services.definition.ICategoryService;
import cz.ucl.logic.app.services.definition.ITagService;
import cz.ucl.logic.app.services.definition.ITaskService;
import cz.ucl.logic.app.services.definition.IUserService;
import cz.ucl.logic.exceptions.EmailAddressAlreadyUsedException;
import cz.ucl.logic.exceptions.InvalidColorException;
import cz.ucl.logic.exceptions.InvalidPropertyException;

/**
 * Everything which is shown (or done) by the AppLogic, has to be related only to the logged in user
 */
public interface IAppLogic extends ICategoryService, IUserService, ITagService, ITaskService {
    //region Mock Data
    /** Generates mock (testing) data */
    void generateMockData() throws InvalidColorException, EmailAddressAlreadyUsedException, InvalidPropertyException;
    //endregion
}
