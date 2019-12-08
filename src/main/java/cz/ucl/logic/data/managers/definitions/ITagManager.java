package cz.ucl.logic.data.managers.definitions;

import cz.ucl.logic.app.entities.definition.Color;
import cz.ucl.logic.app.entities.definition.ITag;
import cz.ucl.logic.exceptions.InvalidColorException;
import cz.ucl.logic.exceptions.TagInUseException;

public interface ITagManager {

    ITag[] getAllForUser(long id);

    void createTag(String title, Color color, long userId) throws InvalidColorException;

    void createTag(String title, long userId);

    void destroyTag(long userId, long id) throws TagInUseException;

    ITag getTagForUserById(long userId, long tagId);

    void updateTag(long userId, long id, String title, Color color);

}
