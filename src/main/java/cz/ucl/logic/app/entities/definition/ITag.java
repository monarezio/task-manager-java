package cz.ucl.logic.app.entities.definition;

public interface ITag extends ITaskOwner {
    long getId();
    IUser getUser();
    String getTitle();
    Color getColor();

    // see ITaskOwner
}
