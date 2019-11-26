package cz.ucl.logic.data.managers.definitions;

import cz.ucl.logic.app.entities.definition.Color;
import cz.ucl.logic.app.entities.definition.ITag;
import cz.ucl.logic.exceptions.InvalidColorException;

public interface ITagManager {

    ITag[] getAllForUser(long id);

    void createTag(String title, Color color, long userId) throws InvalidColorException;

    void createTag(String title, long userId);

    void destroyTag(long id);

    ITag getTagForUserById(long userId, long tagId);

}
