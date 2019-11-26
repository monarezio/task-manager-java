package cz.ucl.logic.app.entities.definition;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public interface ITask {
    //region Getters
    long getId();

    String getTitle();

    String getNote();

    IUser getUser();

    boolean isDone();

    ICategory getCategory();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();
    //endregion

    //region Tags
    ITag[] getTags();

    ITag getTag(int i);

    void saveTag(int i, ITag tag);

    void addTag(ITag tag);

    int tagsCount();
    //endregion

    //region Completion
    void complete();

    void reopen();
    //endregion
}
