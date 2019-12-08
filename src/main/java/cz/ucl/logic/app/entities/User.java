package cz.ucl.logic.app.entities;

import cz.ucl.logic.app.entities.definition.ICategory;
import cz.ucl.logic.app.entities.definition.ITag;
import cz.ucl.logic.app.entities.definition.task.ITask;
import cz.ucl.logic.app.entities.definition.IUser;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

final public class User extends TaskOwner implements IUser {

    private final long id;

    @NotNull
    private final String password;

    @Email
    @NotNull
    private final String email;

    @NotNull
    private final String username;

    private final List<ICategory> categories;
    private final List<ITag> tags;

    public User(long id, String email, String username, String password, ICategory[] categories, ITag[] tags, ITask[] tasks) {
        super(tasks);
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.categories = Arrays.asList(categories);
        this.tags = Arrays.asList(tags);
    }

    public User(String email, String username, String password) {
        this(0, email, username, password, new ICategory[0], new ITag[0], new ITask[0]);
    }

    public User(String email) {
        this(email, null, null);
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public ICategory[] getCategories() {
        return categories.toArray(new ICategory[categories.size()]);
    }

    @Override
    public ICategory getCategory(int i) {
        return categories.get(i);
    }

    @Override
    public void saveCategory(int i, ICategory category) {
        categories.set(i, category);
    }

    @Override
    public void addCategory(ICategory category) {
        categories.add(category);
    }

    @Override
    public int categoriesCount() {
        return categories.size();
    }

    @Override
    public ITag[] getTags() {
        return tags.toArray(new ITag[tags.size()]);
    }

    @Override
    public ITag getTag(int i) {
        return tags.get(i);
    }

    @Override
    public void saveTag(int i, ITag tag) {
        tags.set(i, tag);
    }

    @Override
    public void addTag(ITag tag) {
        tags.add(tag);
    }

    @Override
    public int tagsCount() {
        return tags.size();
    }

    /* Generated by IDEA */

}
