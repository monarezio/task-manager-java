package cz.ucl.ui.cli.views;

import cz.ucl.logic.app.entities.definition.IUser;
import cz.ucl.ui.definition.views.IUserSettingsView;

public class UserSettingsView implements IUserSettingsView {
    @Override
    public String formatUser(IUser user) {
        StringBuilder sb = new StringBuilder("Nastavení uživatele: \n\n");
        sb.append("Username: ");
        sb.append(user.getUsername());
        sb.append("\n");
        sb.append("Email: ");
        sb.append(user.getEmail());
        sb.append("\n");

        return sb.toString();
    }
}
