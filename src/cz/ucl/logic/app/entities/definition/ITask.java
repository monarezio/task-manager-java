package cz.ucl.logic.app.entities.definition;

import java.time.LocalDate;
import java.util.Date;

public interface ITask {
    //region Getters
    int getId();

    String getTitle();

    String getNote();

    IUser getUser();

    boolean isDone();

    ICategory getCategory();

    LocalDate getCreatedAt(); // TODO: Changed Date to LocalDate, is it ok?

    LocalDate getUpdatedAt();
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
