package cz.ucl.ui.definition.menu;

import cz.ucl.logic.app.entities.definition.ICategory;
import cz.ucl.logic.app.entities.definition.ITag;
import cz.ucl.logic.app.entities.definition.IUser;
import cz.ucl.logic.app.entities.definition.TaskDoneStatus;
import cz.ucl.logic.app.entities.definition.task.ITask;
import cz.ucl.logic.app.entities.definition.task.ITaskFilter;
import cz.ucl.logic.app.services.definition.TasksOrder;
import cz.ucl.ui.definition.IUserInterface;

public interface IMenuFactory {
    IMenu createMainMenu(IUserInterface ui);
    IMenu createQuitMenu(IMenu parentMenu);
    IMenu createBackMenu(IMenu parentMenu);
    IMenu createFillFormMenu(IMenu parentMenu);

    IMenu createLoginFormMenu(IMenu parentMenu);
    IMenu createRegistrationFormMenu(IMenu parentMenu);

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
    IMenu createFinishedTasksListMenu(IMenu parentMenu);
    IMenu createNotFinishedTasksListMenu(IMenu parentMenu);

    IMenu createAddTaskCategoryMenu(IMenu parentMenu);
    IMenu createAddTaskTagMenu(IMenu parentMenu, ICategory category, ITag[] tags);
    IMenu createAddTaskBasicInfoFormMenu(IMenu parentMenu, ICategory category, ITag[] tags);

    IMenu createEditTaskCategoryMenu(IMenu parentMenu, int taskId);
    IMenu createEditTaskTagMenu(IMenu parentMenu, ICategory category, ITag[] tags, int taskId);
    IMenu createEditTaskBasicInfoFormMenu(IMenu parentMenu, ICategory category, ITag[] tags, int taskId);
    IMenu createDeleteTaskMenu(IMenu parentMenu, int taskId);

    IMenu createTaskDetail(IMenu parentMenu, int tagId);

    IMenu createTaskFilterMenu(IMenu parentMenu, ITaskFilter taskFilter);
    IMenu createTaskSearchFilterMenu(IMenu parentMenu, ITaskFilter taskFilter);
    IMenu createTaskTagFilterMenu(IMenu parentMenu, ITaskFilter taskFilter);
    IMenu createTaskCategoryFilterMenu(IMenu parentMenu, ITaskFilter taskFilter);
    IMenu createTaskOrderFilterMenu(IMenu parentMenu, ITaskFilter taskFilter);
    IMenu createTaskDoneStatusFilterMenu(IMenu parentMenu, ITaskFilter taskFilter);

    IMenu createTaskSetCategoryFilterActionMenu(IMenu parentMenu, ITaskFilter taskFilter, ICategory category);
    IMenu createTaskSetOrderFilterActionMenu(IMenu parentMenu, ITaskFilter taskFilter, TasksOrder order);

    IMenu createTaskSetStatusFilterActionMenu(IMenu parentMenu, ITaskFilter taskFilter, TaskDoneStatus status);

    IMenu createTaskPageUpAction(IMenu parentMenu, ITaskFilter taskFilter);
    IMenu createTaskPageDownAction(IMenu parentMenu, ITaskFilter taskFilter);

    IMenu createUserSettingsMenu(IMenu parentMenu);
    IMenu createUserSettingsFormMenu(IMenu parentMenu, IUser user);
    IMenu createUserSettingsPasswordFormMenu(IMenu pareMenu);
    IMenu createSettingsUserDestroyFormMenu(IMenu parentMenu);
    IMenu createSettingsLogoutActionMenu(IMenu parentMenu);
}
