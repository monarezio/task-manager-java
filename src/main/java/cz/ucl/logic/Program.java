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
import cz.ucl.logic.app.services.definition.*;
import cz.ucl.logic.app.validators.FieldValidator;
import cz.ucl.logic.app.validators.definition.IValidator;
import cz.ucl.logic.data.hibernate.HibernateSessionFactory;
import cz.ucl.logic.data.hibernate.definitions.IHibernateSessionFactory;
import cz.ucl.logic.data.managers.TagManager;
import cz.ucl.logic.data.managers.UserManager;
import cz.ucl.logic.data.managers.definitions.ITagManager;
import cz.ucl.logic.data.managers.definitions.IUserManager;
import cz.ucl.logic.data.mappers.DAOToEntity.*;
import cz.ucl.logic.data.mappers.definitions.DAOToEntity.*;
import cz.ucl.logic.data.mappers.definitions.entityToDAO.IColorToColorDAOMapper;
import cz.ucl.logic.data.mappers.definitions.entityToDAO.IUserToUserDAOMapper;
import cz.ucl.logic.data.mappers.entityToDAO.ColorToColorDAOMapper;
import cz.ucl.logic.data.mappers.entityToDAO.UserToUserDAOMapper;
import cz.ucl.logic.exceptions.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.Validation;

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
    private final ICategoryDAOToCategory categoryDAOToCategory = CategoryDAOToCategoryMapper.instance;
    private final ITaskDAOToTaskMapper taskDAOToTaskMapper = TaskDAOToTaskMapper.instance;
    private final ITagDAOToTagMapper tagDAOToTagMapper = TagDAOToTagMapper.instance;
    private final IUserDAOToUserMapper userDAOToUserMapper = UserDAOToUserMapper.instance;

    private final IUserToUserDAOMapper userToUserDAOMapper = UserToUserDAOMapper.instance;
    private final IColorToColorDAOMapper colorToColorDAOMapper = ColorToColorDAOMapper.instance;

    private final IHibernateSessionFactory hibernateSessionFactory = new HibernateSessionFactory();
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    private final IUserManager userManager = new UserManager(userDAOToUserMapper, userToUserDAOMapper, hibernateSessionFactory);
    private final ITagManager tagManager = new TagManager(tagDAOToTagMapper, colorToColorDAOMapper, hibernateSessionFactory);

    private final IValidator fieldValidator = new FieldValidator(
            Validation.buildDefaultValidatorFactory().getValidator()
    );

    public Program() {
        userService = new UserService(userManager, bCryptPasswordEncoder, fieldValidator);
        categoryService = new CategoryService(userService);
        tagService = new TagService(userService, tagManager);
        taskService = new TaskService(userService);
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
    public void createCategory(String title) {
        categoryService.createCategory(title);
    }

    @Override
    public void createCategory(String title, Color color) {
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
    public ITask[] getAllTasks() {
        return taskService.getAllTasks();
    }

    @Override
    public ITask[] getAllTasks(TasksOrder order) {
        return taskService.getAllTasks(order);
    }

    @Override
    public ITask[] searchTasksForKeyword(String keyword) {
        return taskService.searchTasksForKeyword(keyword);
    }

    @Override
    public ITask[] getAllTasksByCategory(ICategory category) {
        return taskService.getAllTasksByCategory(category);
    }

    @Override
    public ITask[] getAllTasksByTag(ITag tag) {
        return taskService.getAllTasksByTag(tag);
    }

    @Override
    public ITask[] getAllTasksByTags(ITag[] tag) {
        return taskService.getAllTasksByTags(tag);
    }

    @Override
    public ITask[] getAllTasksByTags(ITag[] tag, ICategory category) {
        return taskService.getAllTasksByTags(tag, category);
    }

    @Override
    public ITask getTaskById(int id) {
        return taskService.getTaskById(id);
    }

    @Override
    public void createTask(String title) {
        taskService.createTask(title);
    }

    @Override
    public void createTask(String title, String note) {
        taskService.createTask(title, note);
    }

    @Override
    public void createTask(String title, String note, ICategory category) {
        taskService.createTask(title, note, category);
    }

    @Override
    public void updateTask(int id, String title, Color color) {
        taskService.updateTask(id, title, color);
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
