package cz.ucl.logic.app.entities.definition;

// import cz.ucl.logic.app.entities.Task;

public interface IUser extends ITaskOwner {
    long getId();
    String getEmail();
    String getUsername();
    String getPassword();

    // see ITaskOwner

    ICategory[] getCategories();
    ICategory getCategory(int i);
    void saveCategory(int i, ICategory category);
    void addCategory(ICategory category);
    int categoriesCount();

    ITag[] getTags();
    ITag getTag(int i);
    void saveTag(int i, ITag tag);
    void addTag(ITag tag);
    int tagsCount();
}
