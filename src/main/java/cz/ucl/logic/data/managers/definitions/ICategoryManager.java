package cz.ucl.logic.data.managers.definitions;

import cz.ucl.logic.app.entities.definition.Color;
import cz.ucl.logic.app.entities.definition.ICategory;
import cz.ucl.logic.exceptions.InvalidColorException;

public interface ICategoryManager {

    ICategory[] getAllForUser(long id);

    void createCategory(String title, Color color, long userId) throws InvalidColorException;

    void createCategory(String title, long userId);

    void destroyCategory(long id);

    ICategory getCategoryForUserById(long userId, long tagId);

    void updateCategory(long id, String title, Color color);

}
