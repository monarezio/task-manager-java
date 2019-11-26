package cz.ucl.logic.data.mappers.DAOToEntity;

import cz.ucl.logic.app.entities.Task;
import cz.ucl.logic.app.entities.definition.ITag;
import cz.ucl.logic.app.entities.definition.ITask;
import cz.ucl.logic.data.dao.TaskDAO;
import cz.ucl.logic.data.mappers.definitions.DAOToEntity.ICategoryDAOToCategory;
import cz.ucl.logic.data.mappers.definitions.DAOToEntity.ITagDAOToTagMapper;
import cz.ucl.logic.data.mappers.definitions.DAOToEntity.ITaskDAOToTaskMapper;
import cz.ucl.logic.data.mappers.definitions.DAOToEntity.IUserDAOToUserMapper;

final public class TaskDAOToTaskMapper implements ITaskDAOToTaskMapper {

    public static final TaskDAOToTaskMapper instance = new TaskDAOToTaskMapper();

    private final IUserDAOToUserMapper userDAOToUserMapper = UserDAOToUserMapper.instance;
    private final ICategoryDAOToCategory categoryDAOToCategory = CategoryDAOToCategoryMapper.instance;
    private final ITagDAOToTagMapper tagDAOToTagMapper = TagDAOToTagMapper.instance;

    private TaskDAOToTaskMapper() {
    }

    /**
     * @param v
     * @return if `v` null returns null
     */
    @Override
    public ITask mapOrNull(TaskDAO v) {
        if (v == null) return null;

        return new Task(
                v.getId(),
                v.getTitle(),
                v.getNote(),
                v.getCreated(),
                v.getUpdated(),
                userDAOToUserMapper.mapOrNull(v.getUser()),
                categoryDAOToCategory.mapOrNull(v.getCategory()),
                v.getTags() != null ? v.getTags().stream().map(tagDAOToTagMapper::mapOrNull).toArray(ITag[]::new) : null,
                v.isDone()
        );
    }
}
