package cz.ucl.logic.app.entities;

import cz.ucl.logic.app.entities.definition.ICategory;
import cz.ucl.logic.app.entities.definition.ITag;
import cz.ucl.logic.app.entities.definition.ITask;
import cz.ucl.logic.app.entities.definition.IUser;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public final class Task implements ITask {

    private final long id;

    @NotNull
    @Size(min = 1)
    private final String title;

    @NotNull
    @Size(min = 1)
    private final String note;

    @NotNull
    private final LocalDateTime created;

    @NotNull
    private final LocalDateTime updated;

    private boolean isDone;

    @NotNull
    private final IUser user;

    private final ICategory category;

    private final List<ITag> tags;

    public Task(long id, String title, String note, LocalDateTime created, LocalDateTime updated, IUser user, ICategory category, ITag[] tags, boolean isDone) {
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
    public long getId() {
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
    public LocalDateTime getCreatedAt() {
        return created;
    }

    @Override
    public LocalDateTime getUpdatedAt() {
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
