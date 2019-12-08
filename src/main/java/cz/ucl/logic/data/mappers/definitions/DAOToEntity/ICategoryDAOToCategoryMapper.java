package cz.ucl.logic.data.mappers.definitions.DAOToEntity;

import cz.ucl.logic.app.entities.definition.ICategory;
import cz.ucl.logic.data.dao.CategoryDAO;
import cz.ucl.logic.data.mappers.definitions.IDeepMapper;
import cz.ucl.logic.data.mappers.definitions.IMapper;

public interface ICategoryDAOToCategoryMapper extends IMapper<CategoryDAO, ICategory>, IDeepMapper<CategoryDAO, ICategory> {
}
