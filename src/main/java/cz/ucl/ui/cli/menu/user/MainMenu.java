package cz.ucl.ui.cli.menu.user;

import cz.ucl.logic.app.entities.definition.IUser;
import cz.ucl.ui.cli.menu.Menu;
import cz.ucl.ui.cli.menu.MenuOption;
import cz.ucl.ui.definition.IUserInterface;
import cz.ucl.ui.definition.menu.IMenu;

public class MainMenu extends Menu {
    public MainMenu(IUserInterface ui, String title) {
        super(null, "main_menu", title);

        this.ui = ui;
        this.logic = ui.getLogic();
    }

    @Override
    protected void build() {
        if(!logic.isUserLoggedIn())
            buildNotLoggedInMenu();
        else
            buildLoggedInMenu();
    }

    @Override
    public void initialize() {
        clearOptions();
        build();
    }

    private void buildNotLoggedInMenu() {
        setDescription(
                "Abyste mohli aplikaci používat, je nutné se nejprve přihlásit.\n\n" +
                        "Pokud ještě nemáte svůj uživatelský účet, je možné se registrovat."
        );

        IMenu loginMenu = ui.getMenuFactory().createLoginFormMenu(this);
        IMenu registerMenu = ui.getMenuFactory().createRegistrationFormMenu(this);
        IMenu quitMenu = ui.getMenuFactory().createQuitMenu(this);

        addOption(new MenuOption(nextOptionNumber(), loginMenu));
        addOption(new MenuOption(nextOptionNumber(), registerMenu));
        addOption(new MenuOption(nextOptionNumber(), quitMenu));
    }

    private void buildLoggedInMenu() {
        IUser user = logic.getUserLoggedIn();

        setDescription(
                "Jste úspěšně přihlášen v aplikaci jako " + user.getUsername() + ".\n\n" +
                        "Máte " + user.categoriesCount() + " úkol/y/ů."
        );

        IMenu allTasksMenu = ui.getMenuFactory().createAllTasksMenu(this);
        IMenu notFinishedTasksMenu = ui.getMenuFactory().createNotFinishedTasksMenu(this);
        IMenu finishedTasksMenu = ui.getMenuFactory().createFinishedTasksMenu(this);
        IMenu addTaskMenu = ui.getMenuFactory().createAddTaskMenu(this);
        IMenu settingsMenu = ui.getMenuFactory().createSettingsMenu(this);
        IMenu quitMenu = ui.getMenuFactory().createQuitMenu(this);

        addOption(new MenuOption(nextOptionNumber(), allTasksMenu));
        addOption(new MenuOption(nextOptionNumber(), notFinishedTasksMenu));
        addOption(new MenuOption(nextOptionNumber(), finishedTasksMenu));
        addOption(new MenuOption(nextOptionNumber(), addTaskMenu));
        addOption(new MenuOption(nextOptionNumber(), settingsMenu));
        addOption(new MenuOption(nextOptionNumber(), quitMenu));
    }
}
