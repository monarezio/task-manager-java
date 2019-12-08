package cz.ucl.logic.data.mappers.DAOToEntity;

import cz.ucl.logic.app.entities.task.Task;
import cz.ucl.logic.app.entities.definition.ITag;
import cz.ucl.logic.app.entities.definition.task.ITask;
import cz.ucl.logic.data.dao.TaskDAO;
import cz.ucl.logic.data.mappers.definitions.DAOToEntity.ICategoryDAOToCategoryMapper;
import cz.ucl.logic.data.mappers.definitions.DAOToEntity.ITagDAOToTagMapper;
import cz.ucl.logic.data.mappers.definitions.DAOToEntity.ITaskDAOToTaskMapper;

final public class TaskDAOToTaskMapper implements ITaskDAOToTaskMapper {

    public static final ITaskDAOToTaskMapper instance = new TaskDAOToTaskMapper();

    private final ICategoryDAOToCategoryMapper categoryDAOToCategoryMapper = CategoryDAOToCategoryMapper.getInstance();
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
                v.getDeadline(),
                null,
                null,
                new ITag[0],
                v.isDone()
        );
    }

    @Override
    public ITask deepMapOrNull(TaskDAO v) {
        if (v == null) return null;

        return new Task(
                v.getId(),
                v.getTitle(),
                v.getNote(),
                v.getCreated(),
                v.getUpdated(),
                v.getDeadline(),
                null,
                categoryDAOToCategoryMapper.mapOrNull(v.getCategory()),
                v.getTags().stream().map(tagDAOToTagMapper::mapOrNull).toArray(ITag[]::new),
                v.isDone()
        );
    }
}
