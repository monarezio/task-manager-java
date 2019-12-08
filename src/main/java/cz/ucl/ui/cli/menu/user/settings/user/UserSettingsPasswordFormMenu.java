package cz.ucl.ui.cli.menu.user.settings.user;

import cz.ucl.ui.cli.forms.FormField;
import cz.ucl.ui.cli.menu.FormMenu;
import cz.ucl.ui.cli.menu.MenuOption;
import cz.ucl.ui.definition.forms.FormFieldType;
import cz.ucl.ui.definition.menu.IMenu;

public class UserSettingsPasswordFormMenu extends FormMenu {
    public UserSettingsPasswordFormMenu(IMenu parentMenu, String title) {
        super(parentMenu, "user_setting_update_password", title);
    }

    @Override
    protected void defineForm() {
        addFormField(new FormField("password", "Password", FormFieldType.SECURE, true));
    }

    @Override
    protected void build() {
        setDescription("Pro úpravu hesla je třeba zadat nové heslo\n");

        IMenu backMenu = ui.getMenuFactory().createBackMenu(this);
        IMenu fillMenu = ui.getMenuFactory().createFillFormMenu(this);

        addOption(new MenuOption(nextOptionNumber(), backMenu));
        addOption(new MenuOption(nextOptionNumber(), fillMenu));
    }
}
