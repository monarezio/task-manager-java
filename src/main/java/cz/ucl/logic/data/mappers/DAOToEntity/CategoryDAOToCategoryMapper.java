package cz.ucl.logic.data.mappers.DAOToEntity;

import cz.ucl.logic.app.entities.Category;
import cz.ucl.logic.app.entities.definition.ICategory;
import cz.ucl.logic.app.entities.definition.task.ITask;
import cz.ucl.logic.data.mappers.definitions.DAOToEntity.ICategoryDAOToCategoryMapper;
import cz.ucl.logic.data.mappers.definitions.DAOToEntity.IColorDAOToColorMapper;
import cz.ucl.logic.data.mappers.definitions.DAOToEntity.ITaskDAOToTaskMapper;
import cz.ucl.logic.data.dao.CategoryDAO;
import cz.ucl.logic.data.mappers.definitions.DAOToEntity.IUserDAOToUserMapper;
import cz.ucl.logic.data.mappers.definitions.entityToDAO.ICategoryToCategoryDAOMapper;

final public class CategoryDAOToCategoryMapper implements ICategoryDAOToCategoryMapper {

    // public static final ICategoryDAOToCategoryMapper instance = new CategoryDAOToCategoryMapper();

    public static ICategoryDAOToCategoryMapper getInstance() {
        return new CategoryDAOToCategoryMapper();
    }

    private final IColorDAOToColorMapper colorDAOToColorMapper = ColorDAOToColorMapper.instance;
    private final ITaskDAOToTaskMapper taskDAOToTaskMapper = TaskDAOToTaskMapper.instance;
    private final IUserDAOToUserMapper userDAOToUserMapper = UserDAOToUserMapper.instance;

    private CategoryDAOToCategoryMapper() {
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
                colorDAOToColorMapper.mapOrNull(v.getColor()),
                new ITask[0]
        );
    }
}
