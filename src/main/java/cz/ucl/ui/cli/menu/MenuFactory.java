package cz.ucl.ui.cli.menu;

import cz.ucl.logic.app.entities.definition.ICategory;
import cz.ucl.logic.app.entities.definition.ITag;
import cz.ucl.logic.app.entities.definition.task.ITaskFilter;
import cz.ucl.ui.cli.forms.FormField;
import cz.ucl.ui.cli.menu.system.BackMenu;
import cz.ucl.ui.cli.menu.system.FillFormMenu;
import cz.ucl.ui.cli.menu.system.QuitMenu;
import cz.ucl.ui.cli.menu.user.settings.SettingsMenu;
import cz.ucl.ui.cli.menu.user.settings.categories.*;
import cz.ucl.ui.cli.menu.user.settings.tags.*;
import cz.ucl.ui.cli.menu.user.tasks.*;
import cz.ucl.ui.cli.menu.user.MainMenu;
import cz.ucl.ui.cli.menu.user.tasks.add.AddTaskCategoryMenu;
import cz.ucl.ui.cli.menu.user.tasks.add.AddTaskFormMenu;
import cz.ucl.ui.cli.menu.user.tasks.add.AddTaskTagMenu;
import cz.ucl.ui.cli.menu.user.tasks.edit.EditTaskCategoryMenu;
import cz.ucl.ui.cli.menu.user.tasks.edit.EditTaskFormMenu;
import cz.ucl.ui.cli.menu.user.tasks.edit.EditTaskTagMenu;
import cz.ucl.ui.cli.menu.user.tasks.filter.TaskSearchFilterMenu;
import cz.ucl.ui.definition.IUserInterface;
import cz.ucl.ui.definition.forms.FormFieldType;
import cz.ucl.ui.definition.menu.IMenu;
import cz.ucl.ui.definition.menu.IMenuFactory;

public class MenuFactory implements IMenuFactory {
    @Override
    public IMenu createMainMenu(IUserInterface ui) {
        return new MainMenu(ui, "Hlavní nabídka");
    }

    @Override
    public IMenu createQuitMenu(IMenu parentMenu) {
        return new QuitMenu(parentMenu, "Ukončit aplikaci");
    }

    @Override
    public IMenu createBackMenu(IMenu parentMenu) {
        return new BackMenu(parentMenu, "Zpět");
    }

    @Override
    public IMenu createFillFormMenu(IMenu parentMenu) {
        return new FillFormMenu(parentMenu, "Vyplnit formulář");
    }

    @Override
    public IMenu createLoginFormMenu(IMenu parentMenu) {
        return new FormMenu(parentMenu, "login", "Přihlásit se") {
            @Override
            protected void defineForm() {
                addFormField(new FormField("email", "E-Mail", FormFieldType.TEXTUAL));
                addFormField(new FormField("password", "Heslo", FormFieldType.SECURE));
            }

            @Override
            protected void build() {
                setDescription("Pro přihlášení je třeba zadat uživatelské jméno a heslo.");

                IMenu backMenu = ui.getMenuFactory().createBackMenu(this);
                IMenu fillMenu = ui.getMenuFactory().createFillFormMenu(this);

                addOption(new MenuOption(nextOptionNumber(), backMenu));
                addOption(new MenuOption(nextOptionNumber(), fillMenu));
            }
        };
    }

    @Override
    public IMenu createRegistrationFormMenu(IMenu parentMenu) {
        return new FormMenu(parentMenu, "register", "Registrovat se") {
            @Override
            protected void defineForm() {
                addFormField(new FormField("email", "E-Mail", FormFieldType.TEXTUAL));
                addFormField(new FormField("username", "Uživatelské jméno", FormFieldType.TEXTUAL));
                addFormField(new FormField("password", "Heslo", FormFieldType.SECURE));
            }

            @Override
            protected void build() {
                setDescription("Pro registraci je třeba zadat e-mail, uživatelské jméno a heslo.");

                IMenu backMenu = ui.getMenuFactory().createBackMenu(this);
                IMenu fillMenu = ui.getMenuFactory().createFillFormMenu(this);

                addOption(new MenuOption(nextOptionNumber(), backMenu));
                addOption(new MenuOption(nextOptionNumber(), fillMenu));
            }
        };
    }

    @Override
    public IMenu createNotFinishedTasksMenu(IMenu parentMenu) {
        return new NotFinishedTasksMenu(parentMenu, "Zobrazit seznam nedokončených úkolů");
    }

    @Override
    public IMenu createFinishedTasksMenu(IMenu parentMenu) {
        return new FinishedTaskMenu(parentMenu, "Zobrazit sezname dokončených úkolů");
    }

    @Override
    public IMenu createAddTaskMenu(IMenu parentMenu) {
        return new AddTaskCategoryMenu(parentMenu, "Zobrazit seznam dokončených úkolů");
    }

    @Override
    public IMenu createSettingsMenu(IMenu parentMenu) {
        return new SettingsMenu(parentMenu, "Zobrazit nastavení aplikace");
    }

    public IMenu createTagsRootMenu(IMenu parentMenu) {
        return new TagsRootMenu(parentMenu, "Nastavení Tagů");
    }

    public IMenu createAddTagFormMenu(IMenu parentMenu) {
        return new TagAddFormMenu(parentMenu, "Přidat tag");
    }

    @Override
    public IMenu createTagDetailMenu(IMenu parentMenu, ITag tag) {
        return new TagDetailMenu(parentMenu, "Detail tagu - " + tag.getTitle(), (int) tag.getId());
    }

    @Override
    public IMenu createTagsListMenu(IMenu parentMenu) {
        return new TagsListMenu(parentMenu, "Seznam všech tagů");
    }

    @Override
    public IMenu createEditTagMenu(IMenu parentMenu, int tagId) {
        return new TagEditFormMenu(parentMenu, "Upravit tag", tagId);
    }

    @Override
    public IMenu createDeleteTagMenu(IMenu parentMenu, int tagId) {
        return new TagDeleteActionMenu(parentMenu, "Smazat tag", tagId);
    }

    @Override
    public IMenu createCategoriesRootMenu(IMenu parentMenu) {
        return new CategoriesRootMenu(parentMenu, "Nastavení Kategorie");
    }

    @Override
    public IMenu createAddCategoryFormMenu(IMenu parentMenu) {
        return new CategoryAddFormMenu(parentMenu, "Přidat kategorii");
    }

    @Override
    public IMenu createCategoryDetailMenu(IMenu parentMenu, ICategory category) {
        return new CategoryDetailMenu(parentMenu, "Detail - " + category.getTitle(), (int) category.getId());
    }

    @Override
    public IMenu createCategoryListMenu(IMenu parentMenu) {
        return new CategoriesListMenu(parentMenu, "Seznam všech kategorií");
    }

    @Override
    public IMenu createEditCategoryMenu(IMenu parentMenu, int categoryId) {
        return new CategoryEditFormMenu(parentMenu, "Upravit kateogrii", categoryId);
    }

    @Override
    public IMenu createDeleteCategoryMenu(IMenu parentMenu, int categoryId) {
        return new CategoryDeleteActionMenu(parentMenu, "Smazat kateogrii", categoryId);
    }

    @Override
    public IMenu createTasksRootMenu(IMenu parentMenu) {
        return new TasksRootMenu(parentMenu, "Seznam úkolů");
    }

    @Override
    public IMenu createTasksListMenu(IMenu parentMenu) {
        return new TasksListMenu(parentMenu, "Zobrazit seznam všech úkolů");
    }

    @Override
    public IMenu createAddTaskCategoryMenu(IMenu parentMenu) {
        return new AddTaskCategoryMenu(parentMenu, "Přidat úkol");
    }

    @Override
    public IMenu createAddTaskTagMenu(IMenu parentMenu, ICategory category, ITag[] tags) {
        return new AddTaskTagMenu(parentMenu, "Task tag menu", category, tags);
    }

    @Override
    public IMenu createAddTaskBasicInfoFormMenu(IMenu parentMenu, ICategory category, ITag[] tags) {
        return new AddTaskFormMenu(parentMenu, "Task formulář", category, tags);
    }

    @Override
    public IMenu createTaskDetail(IMenu parentMenu, int taskId) {
        return new TaskDetailMenu(parentMenu, "Detail úkolu", taskId);
    }

    @Override
    public IMenu createEditTaskCategoryMenu(IMenu parentMenu, int taskId) {
        return new EditTaskCategoryMenu(parentMenu, taskId,"Upravit úkol");
    }

    @Override
    public IMenu createEditTaskTagMenu(IMenu parentMenu, ICategory category, ITag[] tags, int taskId) {
        return new EditTaskTagMenu(parentMenu, "Uprava tagů v úkolu", category, tags, taskId);
    }

    @Override
    public IMenu createEditTaskBasicInfoFormMenu(IMenu parentMenu, ICategory category, ITag[] tags, int taskId) {
        return new EditTaskFormMenu(parentMenu, "Upráva úkolů", taskId, category, tags);
    }

    @Override
    public IMenu createDeleteTaskMenu(IMenu parentMenu, int taskId) {
        return new TaskDeleteActionMenu(parentMenu, "Smazat úkol", taskId);
    }

    @Override
    public IMenu createTaskSearchFilterMenu(IMenu parentMenu, ITaskFilter taskFilter) {
        return new TaskSearchFilterMenu(parentMenu, "Fulltext vyhledávání", taskFilter);
    }
}
