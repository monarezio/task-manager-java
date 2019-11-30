package cz.ucl.logic.data.managers.definitions;

import cz.ucl.logic.app.entities.definition.Color;
import cz.ucl.logic.app.entities.definition.ITag;
import cz.ucl.logic.data.hibernate.definitions.IHibernateSessionFactory;
import cz.ucl.logic.data.mappers.definitions.DAOToEntity.ICategoryDAOToCategory;
import cz.ucl.logic.data.mappers.definitions.entityToDAO.IColorToColorDAOMapper;
import cz.ucl.logic.exceptions.InvalidColorException;

public interface ITagManager {

    ITag[] getAllForUser(long id);

    void createTag(String title, Color color, long userId) throws InvalidColorException;

    void createTag(String title, long userId);

    void destroyTag(long id);

    ITag getTagForUserById(long userId, long tagId);

    void updateTag(long id, String title, Color color);

}
