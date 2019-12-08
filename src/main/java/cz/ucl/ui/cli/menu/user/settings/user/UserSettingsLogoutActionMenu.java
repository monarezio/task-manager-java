package cz.ucl.ui.cli.menu.user.settings.user;

import cz.ucl.logic.exceptions.NotLoggedInException;
import cz.ucl.ui.definition.menu.ActionMenu;
import cz.ucl.ui.definition.menu.IMenu;

public class UserSettingsLogoutActionMenu extends ActionMenu {
    public UserSettingsLogoutActionMenu(IMenu parentMenu, String title) {
        super(parentMenu,"user_logout", title, parentMenu);
    }

    @Override
    public void action() {
        try {
            logic.logoutUser();
        } catch (NotLoggedInException e) {
            e.printStackTrace(); //THIS SHOULD NEVER HAPPEN
        }
    }
}
