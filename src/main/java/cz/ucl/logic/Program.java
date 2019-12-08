package cz.ucl.logic;

import cz.ucl.logic.app.entities.Tag;
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
import cz.ucl.logic.data.dao.TagDAO;
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
import cz.ucl.logic.data.mappers.factory.IMapperFactory;
import cz.ucl.logic.data.mappers.factory.MapperFactory;
import cz.ucl.logic.exceptions.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.Validation;
import java.time.LocalDateTime;
import java.util.Random;

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

    private final IMapperFactory mapperFactory = new MapperFactory();

    private final IHibernateSessionFactory hibernateSessionFactory = new HibernateSessionFactory();
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    private final IUserManager userManager = new UserManager(mapperFactory.getUserDAOUserDaoToUserMapper(), mapperFactory.getUserToUserDaoMapper(), hibernateSessionFactory);
    private final ITagManager tagManager = new TagManager(mapperFactory.getTagDAOToTagMapper(), mapperFactory.getColorToColorDaoMapper(), hibernateSessionFactory);
    private final ICategoryManager categoryManager = new CategoryManager(hibernateSessionFactory, mapperFactory.getCategoryDAOToCategoryMapper(), mapperFactory.getColorToColorDaoMapper());
    private final ITaskManager taskManager = new TaskManager(hibernateSessionFactory, mapperFactory.getCategoryToCategoryDaoMapper(), mapperFactory.getTagToTagDaoMapper(), mapperFactory.getTaskDAOToTaskMapper());

    private final IValidator fieldValidator = new FieldValidator(
            Validation.buildDefaultValidatorFactory().getValidator()
    );

    public Program() {
        userService = new UserService(userManager, bCryptPasswordEncoder, fieldValidator);
        categoryService = new CategoryService(userService, categoryManager);
        tagService = new TagService(userService, tagManager);
        taskService = new TaskService(userService, taskManager);
    }

    private int randomNumberGenerator(int min, int max) {
        return (int) ((Math.random() * ((max - min) + 1)) + min); //TODO: If more time this should be in util class
    }

    @Override
    public void generateMockData() throws InvalidColorException, EmailAddressAlreadyUsedException, InvalidPropertyException {
        String username = "user" + randomNumberGenerator(1, 1000);
        String email = "e" + randomNumberGenerator(1, 1000) + "@" + "gmail.com";
        String password = "123456";

        System.out.println("Setting username: " + username);
        System.out.println("Setting email: " + email);

        registerUserWithoutMockData(email, username, password);

        System.out.println("Password for new user: " + password);
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
    public void destroyTag(int id) throws TagInUseException {
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
    public void registerUserWithoutMockData(String email, String username, String password) throws EmailAddressAlreadyUsedException, InvalidPropertyException {
        userService.registerUserWithoutMockData(email, username, password);
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

    @Override
    public void updateUser(String username, String email) throws EmailAddressAlreadyUsedException, InvalidPropertyException {
        userService.updateUser(username, email);
    }

    @Override
    public void updatePassword(String password) {
        userService.updatePassword(password);
    }
}
