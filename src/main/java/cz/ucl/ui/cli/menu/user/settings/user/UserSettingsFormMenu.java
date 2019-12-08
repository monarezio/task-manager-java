package cz.ucl.ui.cli.menu.user.settings.user;

import cz.ucl.logic.app.entities.definition.IUser;
import cz.ucl.ui.cli.forms.FormField;
import cz.ucl.ui.cli.menu.Menu;
import cz.ucl.ui.cli.menu.MenuOption;
import cz.ucl.ui.definition.forms.FormFieldType;
import cz.ucl.ui.definition.menu.IMenu;

public class UserSettingsFormMenu extends Menu {

    private final IUser user;

    public UserSettingsFormMenu(IMenu parentMenu, String title, IUser user) {
        super(parentMenu, "user_setting_update", title);
        this.user = user;
        defineForm();
    }

    private void defineForm() {
        addFormField(new FormField("username", "Username (" + user.getUsername() + ")", FormFieldType.TEXTUAL));
        addFormField(new FormField("email", "Email (" + user.getEmail() + ")", FormFieldType.TEXTUAL));
    }

    @Override
    protected void build() {
        setDescription("Pro úpravu uživatele je třeba zadat uživatelské jméno a email\n");

        IMenu backMenu = ui.getMenuFactory().createBackMenu(this);
        IMenu fillMenu = ui.getMenuFactory().createFillFormMenu(this);

        addOption(new MenuOption(nextOptionNumber(), backMenu));
        addOption(new MenuOption(nextOptionNumber(), fillMenu));
    }
}
