package cz.ucl.logic.data.mappers.entityToDAO;

import cz.ucl.logic.app.entities.definition.ICategory;
import cz.ucl.logic.data.dao.CategoryDAO;
import cz.ucl.logic.data.mappers.definitions.entityToDAO.ICategoryToCategoryDAOMapper;
import cz.ucl.logic.data.mappers.definitions.entityToDAO.IColorToColorDAOMapper;

public class CategoryToCategoryDAOMapper implements ICategoryToCategoryDAOMapper {

    public final static ICategoryToCategoryDAOMapper instance = new CategoryToCategoryDAOMapper();

    private final IColorToColorDAOMapper colorToColorDAOMapper = ColorToColorDAOMapper.instance;

    private CategoryToCategoryDAOMapper() {
    }

    @Override
    public CategoryDAO mapOrNull(ICategory v) {
        if (v == null) return null;

        CategoryDAO category = new CategoryDAO(v.getId());
        category.setColor(colorToColorDAOMapper.mapOrNull(v.getColor()));
        category.setTitle(v.getTitle());

        return category;
    }
}
