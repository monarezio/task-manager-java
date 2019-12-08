package cz.ucl.ui.cli;

import cz.ucl.logic.IAppLogic;
import cz.ucl.logic.app.entities.definition.Color;
import cz.ucl.logic.app.entities.definition.ICategory;
import cz.ucl.logic.app.entities.definition.ITag;
import cz.ucl.logic.exceptions.*;
import cz.ucl.ui.cli.forms.FormManager;
import cz.ucl.ui.cli.menu.MenuFactory;
import cz.ucl.ui.cli.menu.user.settings.categories.CategoryEditFormMenu;
import cz.ucl.ui.cli.menu.user.settings.tags.TagEditFormMenu;
import cz.ucl.ui.cli.menu.user.tasks.add.AddTaskFormMenu;
import cz.ucl.ui.cli.menu.user.tasks.edit.EditTaskFormMenu;
import cz.ucl.ui.cli.menu.user.tasks.filter.TaskSearchFilterMenu;
import cz.ucl.ui.cli.views.*;
import cz.ucl.ui.definition.forms.IForm;
import cz.ucl.ui.definition.forms.IFormManager;
import cz.ucl.ui.definition.menu.*;
import cz.ucl.ui.definition.views.*;

import java.io.Console;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

public class CLI implements ICLI {

    private IMenuFactory menuFactory;
    private IAppLogic logic;

    private ICategoryView categoryView;
    private ITagView tagView;
    private ITaskView taskView;
    private IMenuView menuView;
    private IFormView formView;

    public CLI() {
        menuFactory = new MenuFactory();

        categoryView = new CategoryView();
        tagView = new TagView();
        taskView = new TaskView();
        menuView = new MenuView();
        formView = new FormView();
    }

    @Override
    public void run(IAppLogic logic) {
        this.logic = logic;

        drawMessage(getWelcomeText());

        IMenu currentMenu;
        IMenu nextMenu = getMainMenu();

        do {
            currentMenu = nextMenu;
            currentMenu.initialize();
            nextMenu = handleMenu(currentMenu);
        }
        while (nextMenu != null);
    }

    //region Forms
    @Override
    public IFormManager createFormManagerForMenu(IForm menu) {
        return new FormManager(menu, this);
    }
    //endregion

    //private final static Scanner sc = new Scanner(System.in);  // TODO: This should not be instanced twice

    //region Prompts
    @Override
    public int promptNumber() {
        Scanner sc = new Scanner(System.in);
        while (!sc.hasNextInt()) sc.next();
        return sc.nextInt();
    }

    @Override
    public String promptString() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    @Override
    public String promptSecureString() {
        Console console = System.console();
        if (console != null) {
            return String.valueOf(console.readPassword(""));
        } else { // if secure input is not supported, fallback to the classic one
            return promptString();
        }
    }

    @Override
    public int promptOption(IMenu menu) {
        return promptNumber();
    }
    //endregion

    //region Logic
    public void invokeAppLogic(IMenu fromMenu, Map<String, String> formData) {
        if (fromMenu.getIdentifier().equals("login")) {
            actionLogin(fromMenu, formData);
        } else if (fromMenu.getIdentifier().equals("register")) {
            actionRegister(fromMenu, formData);
        } else if (fromMenu.getIdentifier().equals("add_tag")) {
            actionAddTag(fromMenu, formData);
        } else if (fromMenu.getIdentifier().equals("edit_tag")) {
            TagEditFormMenu fm = (TagEditFormMenu) fromMenu;
            actionEditTag(fromMenu, formData, fm.getTagId());
        } else if (fromMenu.getIdentifier().equals("add_category")) {
            actionAddCategory(fromMenu, formData);
        } else if (fromMenu.getIdentifier().equals("edit_category")) {
            CategoryEditFormMenu fm = (CategoryEditFormMenu) fromMenu;
            actionEditCategory(fromMenu, formData, fm.getCategoryId());
        } else if (fromMenu.getIdentifier().equals("add_task")) {
            actionAddTask(fromMenu, formData);
        } else if (fromMenu.getIdentifier().equals("task_search_filter")) {
            actionFilterFullTextSearch(fromMenu, formData);
        } else try {
            actionEditTask(fromMenu, formData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // TODO
    }

    public void invokeAppLogic(IMenu fromMenu) {
        if (fromMenu.getIdentifier().equals("main_menu")) {
            actionDashboard(fromMenu);
        }

        drawMessage(menuView.drawOptions(fromMenu)); // TODO Is it fine?
    }
    //endregion

    //region Actions
    private void actionDashboard(IMenu fromMenu) {
        if (logic.isUserLoggedIn()) {
            drawMessage("Jste přihlášen jako: " + logic.getUserLoggedIn().getUsername());
        } else {
            drawMessage("Nejste přihlášen");
        }
    }

    private void actionLogin(IMenu menu, Map<String, String> data) {
        try {
            logic.loginUser(data.get("email"), data.get("password"));
            drawMessage("Přihlášení proběhlo úspěšně");
        } catch (AlreadyLoggedInException | InvalidCredentialsException | InvalidPropertyException e) {
            drawError(e.getMessage());
        }
    }

    private void actionRegister(IMenu menu, Map<String, String> data) {
        try {
            logic.registerUser(data.get("email"), data.get("username"), data.get("password"));
            drawMessage("Registrace proběhla úspěšně");
        } catch (InvalidPropertyException | EmailAddressAlreadyUsedException e) {
            drawError(e.getMessage());
        }
    }

    private void actionAddTag(IMenu menu, Map<String, String> data) {
        try {
            Color color = Color.valueOf(data.get("color").toUpperCase());
            logic.createTag(data.get("title"), color);
            drawMessage("Přidání tagu proběhlo úspěšně");
        } catch (IllegalArgumentException e) {
            drawError("Entered color is not valid.");
        } catch (InvalidColorException e) {
            drawError(e.getMessage());
        }
    }

    private void actionEditTag(IMenu menu, Map<String, String> data, int tagId) {
        try {
            Color color = Color.valueOf(data.get("color").toUpperCase());
            logic.updateTag(tagId, data.get("title"), color);
        } catch (Exception e) {
            drawError(e.getMessage());
        }
    }

    private void actionAddCategory(IMenu menu, Map<String, String> data) {
        try {
            Color color = Color.valueOf(data.get("color").toUpperCase());
            logic.createCategory(data.get("title"), color);
            drawMessage("Přidání kategorie proběhlo úspěšně");
        } catch (IllegalArgumentException e) {
            drawError("Entered color is not valid.");
        } catch (InvalidColorException e) {
            drawError(e.getMessage());
        }
    }

    private void actionEditCategory(IMenu menu, Map<String, String> data, int categoryId) {
        try {
            Color color = Color.valueOf(data.get("color").toUpperCase());
            logic.updateCategory(categoryId, data.get("title"), color);
        } catch (Exception e) {
            drawError(e.getMessage());
        }
    }

    private void actionAddTask(IMenu menu, Map<String, String> data) {
        AddTaskFormMenu fm = (AddTaskFormMenu) menu;
        LocalDateTime deadline = LocalDateTime.parse(data.get("deadline"));
        boolean isDone = Boolean.parseBoolean(data.get("isdone"));

        logic.createTask(data.get("title"), data.get("note"), fm.getCategory(), fm.getTags(), deadline, isDone);
    }

    private void actionEditTask(IMenu menu, Map<String, String> data) {
        EditTaskFormMenu fm = (EditTaskFormMenu) menu;
        LocalDateTime deadline = LocalDateTime.parse(data.get("deadline"));
        boolean isDone = Boolean.parseBoolean(data.get("isdone"));

        logic.updateTask(fm.getTaskId(), data.get("title"), data.get("note"), fm.getCategory(), fm.getTags(), deadline, isDone);
    }

    private void actionFilterFullTextSearch(IMenu menu, Map<String, String> data) {
        TaskSearchFilterMenu tsfm = (TaskSearchFilterMenu) menu;
        tsfm.getTaskFilter().setSearchKeyword(data.get("search"));
    }

    // TODO
    //endregion

    //region Handlers
    public IMenu handleMenu(IMenu currentMenu) {
        drawMessage(menuView.drawSeparator());
        drawMenu(currentMenu);

        invokeAppLogic(currentMenu);

        IMenuOption selectedOption = handleOptions(currentMenu);

        if (currentMenu.isForm()) {
            return handleMenuAsFormForOption(currentMenu, selectedOption);
        } else {
            return handleMenuForOption(currentMenu, selectedOption);
        }
    }

    private IMenuOption handleOptions(IMenu currentMenu) {
        int optionNumber = promptOption(currentMenu);

        if (isValidOption(optionNumber, currentMenu.getValidOptionNumbers()))
            return currentMenu.getOptionForNumber(optionNumber);
        else
            return handleOptions(currentMenu); // I summon the tail rec god. TODO
    }

    private IMenu handleMenuForOption(IMenu currentMenu, IMenuOption selectedOption) {
        IMenu nextMenu = selectedOption.getMenu();

        if (nextMenu.isSystemMenu()) {
            nextMenu = handleSystemMenuChange(currentMenu, nextMenu);
        } else {
            nextMenu = handleUserMenuChange(currentMenu, nextMenu);
        }

        return nextMenu;
    }

    private IMenu handleUserMenuChange(IMenu currentMenu, IMenu nextMenu) {
        return nextMenu;
    }

    private IMenu handleMenuAsFormForOption(IMenu currentMenu, IMenuOption selectedOption) {
        IMenu nextMenu = selectedOption.getMenu();

        if (nextMenu.isSystemMenu()) {
            nextMenu = handleSystemMenuChange(currentMenu, nextMenu);
        } else {
            throw new RuntimeException("You cannot use a non-system menu inside the form menus");
        }

        return nextMenu;
    }

    private IMenu handleSystemMenuChange(IMenu currentMenu, IMenu nextMenu) {
        if (nextMenu.getType() == MenuType.SYSTEM_BACK) {
            nextMenu = currentMenu.getParentMenu();
        } else if (nextMenu.getType() == MenuType.SYSTEM_FILL_FORM) {
            Map<String, String> formData = handleForm(currentMenu);
            invokeAppLogic(currentMenu, formData);
            nextMenu = currentMenu.getParentMenu();
        } else if (nextMenu.getType() == MenuType.SYSTEM_QUIT) {
            // we will close the application with status code 0 (OK) instead of rendering the menu
            System.exit(0);
        } else if (nextMenu.getType() == MenuType.SYSTEM_ACTION && nextMenu instanceof ActionMenu) {
            ActionMenu actionMenu = (ActionMenu) nextMenu;
            actionMenu.action();
            nextMenu = actionMenu.getTargetMenu();
        } else {
            throw new RuntimeException(nextMenu.getType() + " is not valid type of system menu ");
        }

        return nextMenu;
    }

    private Map<String, String> handleForm(IMenu currentMenu) {
        return createFormManagerForMenu(currentMenu).processForm(); // TODO: Is this correct?
    }

    //endregion

    //region Utilities

    /**
     * Checks if validOptions contains testedOption
     */
    private boolean isValidOption(int testedOption, int[] validOptions) {
        return IntStream.of(validOptions).anyMatch(x -> x == testedOption);
    }
    //endregion

    //region Draw Methods
    @Override
    public void drawOutput(String output) {
        System.out.println(output);
    }

    @Override
    public void drawMenu(IMenu menuToBeRendered) {
        System.out.println(menuToBeRendered.render());
    }

    @Override
    public void drawMessage(String message) {
        System.out.println(getMenuView().drawMessage(message));
    }

    @Override
    public void drawError(String message) {
        System.out.println(getMenuView().drawError(message));
    }

    @Override
    public void drawPrompt(String message) {
        System.out.println(getMenuView().drawPrompt(message));
    }
    //endregion


    @Override
    public IAppLogic getLogic() {
        return logic;
    }

    @Override
    public IMenuFactory getMenuFactory() {
        return menuFactory;
    }

    @Override
    public IMenu getMainMenu() {
        return menuFactory.createMainMenu(this); // TODO: Is this valid?
    }

    @Override
    public String getWelcomeText() {
        return "Vítejte v aplikaci Úkolovník 1.0!";
    }

    @Override
    public ICategoryView getCategoryView() {
        return categoryView;
    }

    @Override
    public ITagView getTagView() {
        return tagView;
    }

    @Override
    public ITaskView getTaskView() {
        return taskView;
    }

    @Override
    public IFormView getFormView() {
        return formView;
    }

    @Override
    public IMenuView getMenuView() {
        return menuView;
    }
}

