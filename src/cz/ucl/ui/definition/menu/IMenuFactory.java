package cz.ucl.ui.definition.menu;

import cz.ucl.ui.definition.IUserInterface;

public interface IMenuFactory {
    IMenu createMainMenu(IUserInterface ui);
    IMenu createQuitMenu(IMenu parentMenu);
    IMenu createBackMenu(IMenu parentMenu);
    IMenu createFillFormMenu(IMenu parentMenu);

    IMenu createLoginFormMenu(IMenu parentMenu);
    IMenu createRegistrationFormMenu(IMenu parentMenu);
}
