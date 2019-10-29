package cz.ucl.logic;

import cz.ucl.logic.app.entities.definition.Color;
import cz.ucl.logic.app.entities.definition.ICategory;
import cz.ucl.logic.app.entities.definition.ITag;
import cz.ucl.logic.app.entities.definition.ITask;
import cz.ucl.logic.app.entities.definition.IUser;
import cz.ucl.logic.app.services.CategoryService;
import cz.ucl.logic.app.services.TagService;
import cz.ucl.logic.app.services.TaskService;
import cz.ucl.logic.app.services.UserService;
import cz.ucl.logic.exceptions.AlreadyLoggedInException;
import cz.ucl.logic.exceptions.EmailAddressAlreadyUsedException;
import cz.ucl.logic.exceptions.InvalidCredentialsException;
import cz.ucl.logic.exceptions.NotLoggedInException;

/** This class HAS to honor the Facade design pattern!
 *
 *  No direct functionality should be present!
 *  All functionality should be delegated to service classes
 *
 *  All xxxService attributes have to be private!
 */
public class Program implements IAppLogic {
    private CategoryService categoryService;
    private TagService tagService;
    private TaskService taskService;
    private UserService userService;

    public Program() {
        userService = new UserService();
        categoryService = new CategoryService(userService);
        tagService = new TagService(userService);
        taskService = new TaskService(userService);
    }

    // TODO
}
