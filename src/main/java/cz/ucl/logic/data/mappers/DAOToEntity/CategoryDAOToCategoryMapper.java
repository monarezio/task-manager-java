package cz.ucl.logic.data.mappers.DAOToEntity;

import cz.ucl.logic.app.entities.Category;
import cz.ucl.logic.app.entities.definition.ICategory;
import cz.ucl.logic.app.entities.definition.task.ITask;
import cz.ucl.logic.data.mappers.definitions.DAOToEntity.ICategoryDAOToCategoryMapper;
import cz.ucl.logic.data.mappers.definitions.DAOToEntity.IColorDAOToColorMapper;
import cz.ucl.logic.data.mappers.definitions.DAOToEntity.ITaskDAOToTaskMapper;
import cz.ucl.logic.data.dao.CategoryDAO;
import cz.ucl.logic.data.mappers.definitions.IMapper;
import cz.ucl.logic.data.mappers.factory.IMapperFactory;

final public class CategoryDAOToCategoryMapper implements ICategoryDAOToCategoryMapper {

    private final IMapperFactory factory;

    public CategoryDAOToCategoryMapper(IMapperFactory factory) {
        this.factory = factory;
    }

    /**
     * @param v
     * @return if `v` null returns null
     */
    @Override
    public ICategory mapOrNull(CategoryDAO v) {
        if (v == null) return null;

        return new Category(
                v.getId(),
                null,
                v.getTitle(),
                factory.getColorDaoToColorMapper().mapOrNull(v.getColor()),
                new ITask[0]
        );
    }

    @Override
    public ICategory deepMapOrNull(CategoryDAO v) {
        return new Category(
                v.getId(),
                null,
                v.getTitle(),
                factory.getColorDaoToColorMapper().mapOrNull(v.getColor()),
                v.getTasks().stream().map(t -> factory.getTaskDAOToTaskMapper().mapOrNull(t)).toArray(ITask[]::new)
        );
    }
}
