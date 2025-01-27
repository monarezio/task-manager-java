package cz.ucl.logic.app.entities.definition;

public interface ICategory extends ITaskOwner {
    long getId();
    IUser getUser();
    String getTitle();
    Color getColor();

    // see ITaskOwner
}
