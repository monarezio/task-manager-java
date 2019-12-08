package cz.ucl.logic.data.mappers.entityToDAO;

import cz.ucl.logic.app.entities.definition.ICategory;
import cz.ucl.logic.data.dao.CategoryDAO;
import cz.ucl.logic.data.mappers.definitions.entityToDAO.ICategoryToCategoryDAOMapper;
import cz.ucl.logic.data.mappers.definitions.entityToDAO.IColorToColorDAOMapper;
import cz.ucl.logic.data.mappers.factory.IMapperFactory;

final public class CategoryToCategoryDAOMapper implements ICategoryToCategoryDAOMapper {

    private final IMapperFactory factory;

    public CategoryToCategoryDAOMapper(IMapperFactory factory) {
        this.factory = factory;
    }

    @Override
    public CategoryDAO mapOrNull(ICategory v) {
        if (v == null) return null;

        CategoryDAO category = new CategoryDAO(v.getId());
        category.setColor(factory.getColorToColorDaoMapper().mapOrNull(v.getColor()));
        category.setTitle(v.getTitle());

        return category;
    }
}
