package cz.ucl.ui.definition.menu;

import cz.ucl.ui.definition.IUserInterface;

public interface IMenuFactory {
    IMenu createMainMenu(IUserInterface ui);
    IMenu createQuitMenu(IMenu parentMenu);
    IMenu createBackMenu(IMenu parentMenu);
    IMenu createFillFormMenu(IMenu parentMenu);

    IMenu createLoginFormMenu(IMenu parentMenu);
    IMenu createRegistrationFormMenu(IMenu parentMenu);

    IMenu createAllTasksMenu(IMenu parentMenu);
    IMenu createNotFinishedTasksMenu(IMenu parentMenu);
    IMenu createFinishedTasksMenu(IMenu parentMenu);
    IMenu createAddTaskMenu(IMenu parentMenu);
    IMenu createSettingsMenu(IMenu parentMenu);

    IMenu createTagsMenu(IMenu parentMenu);
    IMenu createAddTagFormMenu(IMenu parentMenu);
    IMenu createDeleteTagFormMenu(IMenu parentMenu);
    IMenu createTagDetailMenuForm(IMenu parentMenu);
    IMenu createTagDetailMenu(IMenu parentMenu, int tagId);
}
