package cz.ucl.ui.definition.menu;

import cz.ucl.logic.app.entities.definition.ICategory;
import cz.ucl.logic.app.entities.definition.ITag;
import cz.ucl.ui.definition.IUserInterface;

public interface IMenuFactory {
    IMenu createMainMenu(IUserInterface ui);
    IMenu createQuitMenu(IMenu parentMenu);
    IMenu createBackMenu(IMenu parentMenu);
    IMenu createFillFormMenu(IMenu parentMenu);

    IMenu createLoginFormMenu(IMenu parentMenu);
    IMenu createRegistrationFormMenu(IMenu parentMenu);

    IMenu createNotFinishedTasksMenu(IMenu parentMenu);
    IMenu createFinishedTasksMenu(IMenu parentMenu);
    IMenu createAddTaskMenu(IMenu parentMenu);
    IMenu createSettingsMenu(IMenu parentMenu);

    IMenu createTagsRootMenu(IMenu parentMenu);
    IMenu createAddTagFormMenu(IMenu parentMenu);
    IMenu createTagDetailMenu(IMenu parentMenu, ITag tag);
    IMenu createTagsListMenu(IMenu parentMenu);
    IMenu createEditTagMenu(IMenu parentMenu, int tagId);
    IMenu createDeleteTagMenu(IMenu parentMenu, int tagId);

    IMenu createCategoriesRootMenu(IMenu parentMenu);
    IMenu createAddCategoryFormMenu(IMenu parentMenu);
    IMenu createCategoryDetailMenu(IMenu parentMenu, ICategory category);
    IMenu createCategoryListMenu(IMenu parentMenu);
    IMenu createEditCategoryMenu(IMenu parentMenu, int tagId);
    IMenu createDeleteCategoryMenu(IMenu parentMenu, int tagId);

    IMenu createTasksRootMenu(IMenu parentMenu);
    IMenu createTasksListMenu(IMenu parentMenu);
    IMenu createAddTaskFormMenu(IMenu parentMenu);
}
