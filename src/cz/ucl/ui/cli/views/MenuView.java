package cz.ucl.ui.cli.views;

import cz.ucl.ui.cli.menu.Menu;
import cz.ucl.ui.definition.menu.IMenu;
import cz.ucl.ui.definition.menu.IMenuOption;
import cz.ucl.ui.definition.views.IMenuView;

public class MenuView implements IMenuView {
    @Override
    public String formatMenuOption(IMenuOption option) {
        StringBuilder sb = new StringBuilder(option.getNumber());
        sb.append(") ");
        sb.append(option.getTitle());
        return sb.toString();
    }

    @Override
    public String formatMenu(Menu menu) {
        return null;
    }

    @Override
    public String drawDescription(Menu menu) {
        return menu.getDescription();
    }

    @Override
    public String drawHeader(IMenu currentMenu) {
        return null;
    }

    @Override
    public String drawSeparator() {
        return null;
    }

    @Override
    public String drawNewLine() {
        return ""; // Return empty since, the println method already adds a \n
    }

    @Override
    public String drawBreadcrumbs(IMenu currentMenu) {
        return "Bread crumbs";
    }

    @Override
    public String drawOptions(IMenu currentMenu) {
        StringBuilder sb = new StringBuilder();

        for(IMenuOption option : currentMenu.getOptions()) {
            sb.append(option.getNumber());
            sb.append(" ");
            sb.append(option.getTitle());
            sb.append("\n");
        }

        return sb.toString();
    }

    @Override
    public String drawMessage(String message) {
        return message;
    }

    @Override
    public String drawError(String message) {
        return message;
    }

    @Override
    public String drawPrompt(String message) {
        return message;
    }
}
