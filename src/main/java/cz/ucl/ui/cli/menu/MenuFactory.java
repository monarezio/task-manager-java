package cz.ucl.ui.cli.menu;

import cz.ucl.logic.app.entities.definition.Color;
import cz.ucl.logic.app.entities.definition.ITag;
import cz.ucl.ui.cli.forms.FormField;
import cz.ucl.ui.cli.menu.system.BackMenu;
import cz.ucl.ui.cli.menu.system.FillFormMenu;
import cz.ucl.ui.cli.menu.system.QuitMenu;
import cz.ucl.ui.cli.menu.user.settings.SettingsMenu;
import cz.ucl.ui.cli.menu.user.settings.tags.TagDetailMenu;
import cz.ucl.ui.cli.menu.user.settings.tags.TagsMenu;
import cz.ucl.ui.cli.menu.user.tasks.AddTaskMenu;
import cz.ucl.ui.cli.menu.user.tasks.AllTasksMenu;
import cz.ucl.ui.cli.menu.user.tasks.FinishedTaskMenu;
import cz.ucl.ui.cli.menu.user.MainMenu;
import cz.ucl.ui.cli.menu.user.tasks.NotFinishedTasksMenu;
import cz.ucl.ui.definition.IUserInterface;
import cz.ucl.ui.definition.forms.FormFieldType;
import cz.ucl.ui.definition.menu.IMenu;
import cz.ucl.ui.definition.menu.IMenuFactory;

import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public IMenu createAllTasksMenu(IMenu parentMenu) {
        return new AllTasksMenu(parentMenu, "Zobrazit seznam všech úkolů");
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
        return new AddTaskMenu(parentMenu, "Zobrazit seznam dokončených úkolů");
    }

    @Override
    public IMenu createSettingsMenu(IMenu parentMenu) {
        return new SettingsMenu(parentMenu, "Zobrazit nastavení aplikace");
    }

    public IMenu createTagsMenu(IMenu parentMenu) {
        return new TagsMenu(parentMenu, "Nastavení Tagů");
    }

    public IMenu createAddTagFormMenu(IMenu parentMenu) {
        return new FormMenu(parentMenu, "add_tag", "Přidat Tag") {
            @Override
            protected void defineForm() {
                addFormField(new FormField("title", "Název", FormFieldType.TEXTUAL));
                addFormField(new FormField("color", "Barva", FormFieldType.TEXTUAL, false));
            }

            @Override
            protected void build() {

                String colors = Stream.of(Color.values())
                        .map(Enum::toString)
                        .collect(Collectors.joining(", "));

                setDescription("Pro přidání tagu je třeba zadat název.\n\nMožné barvy jsou: " + colors);

                IMenu backMenu = ui.getMenuFactory().createBackMenu(this);
                IMenu fillMenu = ui.getMenuFactory().createFillFormMenu(this);

                addOption(new MenuOption(nextOptionNumber(), backMenu));
                addOption(new MenuOption(nextOptionNumber(), fillMenu));
            }
        };
    }

    @Override
    public IMenu createDeleteTagFormMenu(IMenu parentMenu) {
        return new FormMenu(parentMenu, "tag_delete", "Smazat tag") {
            @Override
            protected void defineForm() {
                addFormField(new FormField("tag_id", "ID Tagu", FormFieldType.NUMERICAL, false));
            }

            @Override
            protected void build() {
                ITag[] tags = logic.getAllTags();
                setDescription("Pro smazání tagu je třeba zadat jeho ID.\n" + ui.getTagView().formatTagList(tags));

                IMenu backMenu = ui.getMenuFactory().createBackMenu(this);
                IMenu fillMenu = ui.getMenuFactory().createFillFormMenu(this);

                addOption(new MenuOption(nextOptionNumber(), backMenu));
                addOption(new MenuOption(nextOptionNumber(), fillMenu));
            }
        };
    }

    @Override
    public IMenu createTagDetailMenuForm(IMenu parentMenu) {
        return new FormMenu(parentMenu, "tag_detail_form", "Detail tagu") {
            @Override
            protected void defineForm() {
                addFormField(new FormField("tag_id", "ID Tagu", FormFieldType.NUMERICAL, false));
            }

            @Override
            protected void build() {
                ITag[] tags = logic.getAllTags();
                setDescription("Pro zobrazení detail tagu je třeba zadat jeho ID.\n" + ui.getTagView().formatTagList(tags));

                IMenu backMenu = ui.getMenuFactory().createBackMenu(this);
                IMenu fillMenu = ui.getMenuFactory().createFillFormMenu(this);

                addOption(new MenuOption(nextOptionNumber(), backMenu));
                addOption(new MenuOption(nextOptionNumber(), fillMenu));
            }
        };
    }

    @Override
    public IMenu createTagDetailMenu(IMenu parentMenu, int tagId) {
        return new TagDetailMenu(parentMenu, "Detail tagu", tagId);
    }

    // TODO
}
