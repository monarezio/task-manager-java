package cz.ucl.ui.cli.menu.user.settings.user;

import cz.ucl.logic.app.entities.definition.IUser;
import cz.ucl.ui.cli.menu.Menu;
import cz.ucl.ui.cli.menu.MenuOption;
import cz.ucl.ui.definition.menu.IMenu;

public class UserSettingsMenu extends Menu {
    public UserSettingsMenu(IMenu parentMenu, String title) {
        super(parentMenu, "user_settings", title);
    }

    @Override
    protected void build() {
        IUser user = logic.getUserLoggedIn();

        setDescription(ui.getSettingsMenuView().formatUser(user));

        IMenu changeInfo = ui.getMenuFactory().createUserSettingsFormMenu(this, user);
        IMenu changePassword = ui.getMenuFactory().createUserSettingsPasswordFormMenu(this);

        IMenu root = this;
        while(root.getParentMenu() != null) {
            root = root.getParentMenu();
        }

        IMenu logoutUser = ui.getMenuFactory().createSettingsLogoutActionMenu(root);
        IMenu destroyUser = ui.getMenuFactory().createSettingsUserDestroyFormMenu(root);

        IMenu backMenu = ui.getMenuFactory().createBackMenu(this);

        addOption(new MenuOption(nextOptionNumber(), changeInfo));
        addOption(new MenuOption(nextOptionNumber(), changePassword));
        addOption(new MenuOption(nextOptionNumber(), destroyUser));
        addOption(new MenuOption(nextOptionNumber(), logoutUser));
        addOption(new MenuOption(nextOptionNumber(), backMenu));
    }

    @Override
    public void initialize() {
        clearOptions();
        build();
    }
}
