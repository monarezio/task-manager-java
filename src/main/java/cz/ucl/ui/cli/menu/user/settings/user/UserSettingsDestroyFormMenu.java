package cz.ucl.ui.cli.menu.user.settings.user;

import cz.ucl.ui.cli.forms.FormField;
import cz.ucl.ui.cli.menu.FormMenu;
import cz.ucl.ui.cli.menu.MenuOption;
import cz.ucl.ui.definition.forms.FormFieldType;
import cz.ucl.ui.definition.menu.ActionMenu;
import cz.ucl.ui.definition.menu.IMenu;

public class UserSettingsDestroyFormMenu extends FormMenu {
    public UserSettingsDestroyFormMenu(IMenu parentMenu, String title) {
        super(parentMenu, "delete_user", title);
    }

    @Override
    protected void defineForm() {
        addFormField(new FormField("is_sure", "Jste si jistý? (ano/ne)", FormFieldType.BOOLEAN));
    }

    @Override
    protected void build() {
        setDescription("Chystáte se smazat uživatelský účet.\n");

        IMenu backMenu = ui.getMenuFactory().createBackMenu(this);
        IMenu fillMenu = ui.getMenuFactory().createFillFormMenu(this);

        addOption(new MenuOption(nextOptionNumber(), backMenu));
        addOption(new MenuOption(nextOptionNumber(), fillMenu));
    }
}
