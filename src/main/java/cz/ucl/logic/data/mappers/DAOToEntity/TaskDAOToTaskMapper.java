package cz.ucl.logic.data.mappers.DAOToEntity;

import cz.ucl.logic.app.entities.task.Task;
import cz.ucl.logic.app.entities.definition.ITag;
import cz.ucl.logic.app.entities.definition.task.ITask;
import cz.ucl.logic.data.dao.TaskDAO;
import cz.ucl.logic.data.mappers.definitions.DAOToEntity.ICategoryDAOToCategoryMapper;
import cz.ucl.logic.data.mappers.definitions.DAOToEntity.ITagDAOToTagMapper;
import cz.ucl.logic.data.mappers.definitions.DAOToEntity.ITaskDAOToTaskMapper;
import cz.ucl.logic.data.mappers.factory.IMapperFactory;

final public class TaskDAOToTaskMapper implements ITaskDAOToTaskMapper {

    private final IMapperFactory factory;

    public TaskDAOToTaskMapper(IMapperFactory factory) {
        this.factory = factory;
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
                factory.getCategoryDAOToCategoryMapper().mapOrNull(v.getCategory()),
                v.getTags().stream().map(t -> factory.getTagDAOToTagMapper().mapOrNull(t)).toArray(ITag[]::new),
                v.isDone()
        );
    }
}
