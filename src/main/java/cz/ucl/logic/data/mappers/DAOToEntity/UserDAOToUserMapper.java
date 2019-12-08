package cz.ucl.logic.data.mappers.DAOToEntity;

import cz.ucl.logic.app.entities.User;
import cz.ucl.logic.app.entities.definition.ICategory;
import cz.ucl.logic.app.entities.definition.ITag;
import cz.ucl.logic.app.entities.definition.task.ITask;
import cz.ucl.logic.app.entities.definition.IUser;
import cz.ucl.logic.data.mappers.definitions.DAOToEntity.ICategoryDAOToCategoryMapper;
import cz.ucl.logic.data.mappers.definitions.DAOToEntity.ITagDAOToTagMapper;
import cz.ucl.logic.data.mappers.definitions.DAOToEntity.ITaskDAOToTaskMapper;
import cz.ucl.logic.data.mappers.definitions.DAOToEntity.IUserDAOToUserMapper;
import cz.ucl.logic.data.dao.UserDAO;

final public class UserDAOToUserMapper implements IUserDAOToUserMapper {

    public static final IUserDAOToUserMapper instance = new UserDAOToUserMapper();

    private final ICategoryDAOToCategoryMapper categoryDAOToCategory = CategoryDAOToCategoryMapper.getInstance();
    private final ITaskDAOToTaskMapper taskDAOToTaskMapper = TaskDAOToTaskMapper.instance;
    private final ITagDAOToTagMapper tagDAOToTagMapper = TagDAOToTagMapper.instance;

    private UserDAOToUserMapper() {
    }

    /**
     * @param v
     * @return if `v` null returns null
     */
    @Override
    public IUser mapOrNull(UserDAO v) {
        if (v == null) return null;

        return new User(
                v.getId(),
                v.getEmail(),
                v.getUsername(),
                v.getPassword(),
                new ICategory[0],
                new ITag[0],
                new ITask[0]
        );
    }
}
