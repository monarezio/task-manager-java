package cz.ucl.logic.data.mappers.definitions.entityToDAO;

import cz.ucl.logic.app.entities.Category;
import cz.ucl.logic.app.entities.definition.ICategory;
import cz.ucl.logic.data.dao.CategoryDAO;
import cz.ucl.logic.data.mappers.definitions.IMapper;

public interface ICategoryToCategoryDAOMapper extends IMapper<ICategory, CategoryDAO> {
}
