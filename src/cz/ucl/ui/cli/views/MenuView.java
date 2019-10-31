package cz.ucl.ui.cli.views;

import com.sun.tools.javac.util.ArrayUtils;
import cz.ucl.ui.cli.menu.Menu;
import cz.ucl.ui.definition.menu.IMenu;
import cz.ucl.ui.definition.menu.IMenuOption;
import cz.ucl.ui.definition.views.IMenuView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MenuView implements IMenuView {
    @Override
    public String formatMenuOption(IMenuOption option) {
        final StringBuilder sb = new StringBuilder(option.getNumber());
        sb.append(option.getNumber());
        sb.append(") ");
        sb.append(option.getTitle());
        sb.append("\n");
        return sb.toString();
    }

    @Override
    public String formatMenu(IMenu menu) {
        // TODO
        final StringBuilder sb = new StringBuilder();
        sb.append(drawBreadcrumbs(menu));
        sb.append(drawNewLine());
        sb.append(drawNewLine());
        sb.append(drawDescription(menu));
        sb.append(drawNewLine());

        return sb.toString();
    }

    @Override
    public String drawDescription(IMenu menu) {
        return menu.getDescription();
    }

    @Override
    public String drawHeader(IMenu currentMenu) {
        final StringBuilder sb = new StringBuilder(currentMenu.getTitle());
        sb.append(drawSeparator());

        return sb.toString();
    }

    @Override
    public String drawSeparator() {
        return "------------------------";
    }

    @Override
    public String drawNewLine() {
        return "\n"; // Return empty since, the println method already adds a \n
    }

    @Override
    public String drawBreadcrumbs(IMenu currentMenu) {
        final StringBuilder sb = new StringBuilder("Nacházíte se v menu: ");

        IMenu menu = currentMenu;
        List<IMenu> menus = new ArrayList<>();
        while(menu != null) {
            menus.add(menu);
            menu = menu.getParentMenu();
        }

        final List<String> titles = menus.stream().map(IMenu::getTitle).collect(Collectors.toList());
        Collections.reverse(titles);

        sb.append(String.join(" -> ", titles));

        return sb.toString();
    }

    @Override
    public String drawOptions(IMenu currentMenu) {
        StringBuilder sb = new StringBuilder();
        sb.append(drawNewLine());

        for(IMenuOption option : currentMenu.getOptions()) {
            sb.append(formatMenuOption(option));
        }
        sb.append(drawMessage("Zadejte číslo volby: "));

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
        final StringBuilder sb = new StringBuilder(message);
        sb.append("\n");
        sb.append(drawSeparator());

        return sb.toString();
    }
}
