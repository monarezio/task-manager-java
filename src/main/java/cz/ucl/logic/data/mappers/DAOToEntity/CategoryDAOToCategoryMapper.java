package cz.ucl.logic.data.mappers.DAOToEntity;

import cz.ucl.logic.app.entities.Category;
import cz.ucl.logic.app.entities.definition.ICategory;
import cz.ucl.logic.app.entities.definition.ITask;
import cz.ucl.logic.data.mappers.definitions.DAOToEntity.ICategoryDAOToCategory;
import cz.ucl.logic.data.mappers.definitions.DAOToEntity.IColorDAOToColorMapper;
import cz.ucl.logic.data.mappers.definitions.DAOToEntity.ITaskDAOToTaskMapper;
import cz.ucl.logic.data.dao.CategoryDAO;
import cz.ucl.logic.data.mappers.definitions.DAOToEntity.IUserDAOToUserMapper;

final public class CategoryDAOToCategoryMapper implements ICategoryDAOToCategory {

    public static final ICategoryDAOToCategory instance = new CategoryDAOToCategoryMapper();

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
                userDAOToUserMapper.mapOrNull(v.getUser()),
                v.getTitle(),
                colorDAOToColorMapper.mapOrNull(v.getColor()),
                v.getTasks() != null ?
                        v.getTasks()
                                .stream()
                                .map(taskDAOToTaskMapper::mapOrNull)
                                .toArray(ITask[]::new) : null
        );
    }
}
