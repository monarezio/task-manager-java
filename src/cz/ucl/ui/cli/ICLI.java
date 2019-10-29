package cz.ucl.ui.cli;

import cz.ucl.ui.definition.IUserInterface;
import cz.ucl.ui.definition.menu.IMenu;

public interface ICLI extends IUserInterface {
    IMenu handleMenu(IMenu currentMenu);
}
