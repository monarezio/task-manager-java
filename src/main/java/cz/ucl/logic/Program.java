package cz.ucl.logic;

import cz.ucl.logic.app.entities.definition.Color;
import cz.ucl.logic.app.entities.definition.ICategory;
import cz.ucl.logic.app.entities.definition.ITag;
import cz.ucl.logic.app.entities.definition.task.ITask;
import cz.ucl.logic.app.entities.definition.IUser;
import cz.ucl.logic.app.entities.definition.task.ITaskFilter;
import cz.ucl.logic.app.entities.definition.task.ITasksCollection;
import cz.ucl.logic.app.services.CategoryService;
import cz.ucl.logic.app.services.TagService;
import cz.ucl.logic.app.services.TaskService;
import cz.ucl.logic.app.services.UserService;
import cz.ucl.logic.app.services.definition.*;
import cz.ucl.logic.app.validators.FieldValidator;
import cz.ucl.logic.app.validators.definition.IValidator;
import cz.ucl.logic.data.hibernate.HibernateSessionFactory;
import cz.ucl.logic.data.hibernate.definitions.IHibernateSessionFactory;
import cz.ucl.logic.data.managers.CategoryManager;
import cz.ucl.logic.data.managers.TagManager;
import cz.ucl.logic.data.managers.TaskManager;
import cz.ucl.logic.data.managers.UserManager;
import cz.ucl.logic.data.managers.definitions.ICategoryManager;
import cz.ucl.logic.data.managers.definitions.ITagManager;
import cz.ucl.logic.data.managers.definitions.ITaskManager;
import cz.ucl.logic.data.managers.definitions.IUserManager;
import cz.ucl.logic.data.mappers.DAOToEntity.*;
import cz.ucl.logic.data.mappers.definitions.DAOToEntity.*;
import cz.ucl.logic.data.mappers.definitions.entityToDAO.*;
import cz.ucl.logic.data.mappers.entityToDAO.*;
import cz.ucl.logic.exceptions.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.Validation;
import java.time.LocalDateTime;

/**
 * This class HAS to honor the Facade design pattern!
 * <p>
 * No direct functionality should be present!
 * All functionality should be delegated to service classes
 * <p>
 * All xxxService attributes have to be private!
 */
public class Program implements IAppLogic {
    private final ICategoryService categoryService;
    private final ITagService tagService;
    private final ITaskService taskService;
    private final IUserService userService;

    private final IColorDAOToColorMapper colorDAOToColorMapper = ColorDAOToColorMapper.instance;
    private final ICategoryDAOToCategoryMapper categoryDAOToCategory = CategoryDAOToCategoryMapper.getInstance();
    private final ITaskDAOToTaskMapper taskDAOToTaskMapper = TaskDAOToTaskMapper.instance;
    private final ITagDAOToTagMapper tagDAOToTagMapper = TagDAOToTagMapper.instance;
    private final IUserDAOToUserMapper userDAOToUserMapper = UserDAOToUserMapper.instance;

    private final IUserToUserDAOMapper userToUserDAOMapper = UserToUserDAOMapper.instance;
    private final IColorToColorDAOMapper colorToColorDAOMapper = ColorToColorDAOMapper.instance;
    private final ICategoryToCategoryDAOMapper categoryToCategoryDAOMapper = CategoryToCategoryDAOMapper.instance;
    private final ITagToTagDAOMapper tagToTagDAOMapper = TagToTagDAOMapper.instance;
    private final ITaskToTaskDAOMapper taskToTaskDAOMapper = TaskToTaskDAOMapper.instance;

    private final IHibernateSessionFactory hibernateSessionFactory = new HibernateSessionFactory();
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    private final IUserManager userManager = new UserManager(userDAOToUserMapper, userToUserDAOMapper, hibernateSessionFactory);
    private final ITagManager tagManager = new TagManager(tagDAOToTagMapper, colorToColorDAOMapper, hibernateSessionFactory);
    private final ICategoryManager categoryManager = new CategoryManager(hibernateSessionFactory, categoryDAOToCategory, colorToColorDAOMapper);
    private final ITaskManager taskManager = new TaskManager(hibernateSessionFactory, categoryToCategoryDAOMapper, tagToTagDAOMapper, taskDAOToTaskMapper);

    private final IValidator fieldValidator = new FieldValidator(
            Validation.buildDefaultValidatorFactory().getValidator()
    );

    public Program() {
        userService = new UserService(userManager, bCryptPasswordEncoder, fieldValidator);
        categoryService = new CategoryService(userService, categoryManager);
        tagService = new TagService(userService, tagManager);
        taskService = new TaskService(userService, taskManager);
    }

    @Override
    public void generateMockData() {
        // TODO: Implement
    }

    @Override
    public ICategory[] getAllCategories() {
        return categoryService.getAllCategories();
    }

    @Override
    public ICategory getCategoryById(int id) {
        return categoryService.getCategoryById(id);
    }

    @Override
    public void createCategory(String title) throws InvalidColorException {
        categoryService.createCategory(title);
    }

    @Override
    public void createCategory(String title, Color color) throws InvalidColorException {
        categoryService.createCategory(title, color);
    }

    @Override
    public void updateCategory(int id, String title, Color color) {
        categoryService.updateCategory(id, title, color);
    }

    @Override
    public void destroyCategory(int id) {
        categoryService.destroyCategory(id);
    }

    @Override
    public ITag[] getAllTags() {
        return tagService.getAllTags();
    }

    @Override
    public ITag getTagById(int id) {
        return tagService.getTagById(id);
    }

    @Override
    public void createTag(String title) {
        tagService.createTag(title);
    }

    @Override
    public void createTag(String title, Color color) throws InvalidColorException {
        tagService.createTag(title, color);
    }

    @Override
    public void updateTag(int id, String title, Color color) {
        tagService.updateTag(id, title, color);
    }

    @Override
    public void destroyTag(int id) {
        tagService.destroyTag(id);
    }

    @Override
    public ITasksCollection getAllTasks(ITaskFilter taskFilter) {
        return taskService.getAllTasks(taskFilter);
    }

    @Override
    public ITask getTaskById(int id) {
        return taskService.getTaskById(id);
    }

    @Override
    public void createTask(String title, boolean isDone) {
        taskService.createTask(title, isDone);
    }

    @Override
    public void createTask(String title, String note, boolean isDone) {
        taskService.createTask(title, note, isDone);
    }

    @Override
    public void createTask(String title, String note, ICategory category, boolean isDone) {
        taskService.createTask(title, note, category, isDone);
    }

    @Override
    public void createTask(String title, String note, ICategory category, ITag[] tags, LocalDateTime deadline, boolean isDone) {
        taskService.createTask(title, note, category, tags, deadline, isDone);
    }

    @Override
    public void updateTask(int id, String title, String note, ICategory category, ITag[] tags, LocalDateTime deadline, boolean isDone) {
        taskService.updateTask(id, title, note, category, tags, deadline, isDone);
    }

    @Override
    public void destroyTask(int id) {
        taskService.destroyTask(id);
    }

    @Override
    public void loginUser(String email, String password) throws AlreadyLoggedInException, InvalidCredentialsException, InvalidPropertyException {
        userService.loginUser(email, password);
    }

    @Override
    public void logoutUser() throws NotLoggedInException {
        userService.logoutUser();
    }

    @Override
    public void registerUser(String email, String username, String password) throws EmailAddressAlreadyUsedException, InvalidPropertyException {
        userService.registerUser(email, username, password);
    }

    @Override
    public boolean isUserLoggedIn() {
        return userService.isUserLoggedIn();
    }

    @Override
    public IUser getUserLoggedIn() {
        return userService.getUserLoggedIn();
    }

    @Override
    public void destroyUserLoggedIn() throws NotLoggedInException {
        userService.destroyUserLoggedIn();
    }
}
