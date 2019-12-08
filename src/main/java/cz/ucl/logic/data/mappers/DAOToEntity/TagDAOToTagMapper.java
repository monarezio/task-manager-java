package cz.ucl.logic.data.mappers.DAOToEntity;

import cz.ucl.logic.app.entities.Tag;
import cz.ucl.logic.app.entities.definition.ITag;
import cz.ucl.logic.app.entities.definition.task.ITask;
import cz.ucl.logic.data.dao.TagDAO;
import cz.ucl.logic.data.mappers.definitions.DAOToEntity.IColorDAOToColorMapper;
import cz.ucl.logic.data.mappers.definitions.DAOToEntity.ITagDAOToTagMapper;
import cz.ucl.logic.data.mappers.definitions.DAOToEntity.ITaskDAOToTaskMapper;
import cz.ucl.logic.data.mappers.definitions.DAOToEntity.IUserDAOToUserMapper;
import cz.ucl.logic.data.mappers.factory.IMapperFactory;

final public class TagDAOToTagMapper implements ITagDAOToTagMapper {

    private final IMapperFactory factory;

    public TagDAOToTagMapper(IMapperFactory factory) {
        this.factory = factory;
    }

    /**
     * @param v
     * @return if `v` null returns null
     */
    @Override
    public ITag mapOrNull(TagDAO v) {
        if (v == null) return null;

        return new Tag(
                v.getId(),
                null,
                v.getTitle(),
                factory.getColorDaoToColorMapper().mapOrNull(v.getColor()),
                new ITask[0]
        );
    }

    @Override
    public ITag deepMapOrNull(TagDAO v) {
        return new Tag(
                v.getId(),
                null,
                v.getTitle(),
                factory.getColorDaoToColorMapper().mapOrNull(v.getColor()),
                v.getTasks().stream()
                        .map(t -> factory.getTaskDAOToTaskMapper().mapOrNull(t))
                        .toArray(ITask[]::new)
        );
    }
}
