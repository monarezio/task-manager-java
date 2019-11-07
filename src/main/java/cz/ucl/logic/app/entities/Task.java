package cz.ucl.logic.app.entities;

import cz.ucl.logic.app.entities.definition.ICategory;
import cz.ucl.logic.app.entities.definition.ITag;
import cz.ucl.logic.app.entities.definition.ITask;
import cz.ucl.logic.app.entities.definition.IUser;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public final class Task implements ITask {

    private final int id;
    private final String title;
    private final String note;
    private final LocalDate created;
    private final LocalDate updated;
    private boolean isDone;

    private final IUser user;

    private final ICategory category;

    private final List<ITag> tags;

    public Task(int id, String title, String note, LocalDate created, LocalDate updated, IUser user, ICategory category, ITag[] tags, boolean isDone) {
        this.id = id;
        this.title = title;
        this.note = note;
        this.created = created;
        this.updated = updated;
        this.user = user;
        this.category = category;
        this.tags = Arrays.asList(tags);
        this.isDone = isDone;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getNote() {
        return note;
    }

    @Override
    public IUser getUser() {
        return user;
    }

    @Override
    public boolean isDone() {
        return isDone;
    }

    @Override
    public ICategory getCategory() {
        return category;
    }

    @Override
    public LocalDate getCreatedAt() {
        return created;
    }

    @Override
    public LocalDate getUpdatedAt() {
        return updated;
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

    @Override
    public void complete() {
        isDone = true;
    }

    @Override
    public void reopen() {
        isDone = false;
    }
}
