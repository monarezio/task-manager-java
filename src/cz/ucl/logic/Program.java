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
    private ICategoryService categoryService;
    private ITagService tagService;
    private ITaskService taskService;
    private IUserService userService;

    public Program() {
        userService = new UserService();
        categoryService = new CategoryService(userService);
        tagService = new TagService(userService);
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
    public void createTag(String title, Color color) {
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
    public void loginUser(String email, String password) throws AlreadyLoggedInException, InvalidCredentialsException {
        userService.loginUser(email, password);
    }

    @Override
    public void logoutUser() throws NotLoggedInException {
        userService.logoutUser();
    }

    @Override
    public void registerUser(String email, String username, String password) throws EmailAddressAlreadyUsedException {
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
