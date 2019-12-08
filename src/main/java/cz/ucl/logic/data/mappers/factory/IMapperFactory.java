package cz.ucl.logic.data.mappers.factory;

import cz.ucl.logic.data.mappers.definitions.DAOToEntity.*;
import cz.ucl.logic.data.mappers.definitions.entityToDAO.*;

public interface IMapperFactory {

    ICategoryDAOToCategoryMapper getCategoryDAOToCategoryMapper();
    IColorDAOToColorMapper getColorDaoToColorMapper();
    ITagDAOToTagMapper getTagDAOToTagMapper();
    ITaskDAOToTaskMapper getTaskDAOToTaskMapper();
    IUserDAOToUserMapper getUserDAOUserDaoToUserMapper();

    ICategoryToCategoryDAOMapper getCategoryToCategoryDaoMapper();
    IColorToColorDAOMapper getColorToColorDaoMapper();
    ITagToTagDAOMapper getTagToTagDaoMapper();
    ITaskToTaskDAOMapper getTaskToTaskDaoMapper();
    IUserToUserDAOMapper getUserToUserDaoMapper();

}
