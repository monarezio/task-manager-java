package cz.ucl.logic.data.mappers.DAOToEntity;

import cz.ucl.logic.app.entities.Tag;
import cz.ucl.logic.app.entities.definition.ITag;
import cz.ucl.logic.app.entities.definition.ITask;
import cz.ucl.logic.data.dao.TagDAO;
import cz.ucl.logic.data.mappers.definitions.DAOToEntity.IColorDAOToColorMapper;
import cz.ucl.logic.data.mappers.definitions.DAOToEntity.ITagDAOToTagMapper;
import cz.ucl.logic.data.mappers.definitions.DAOToEntity.ITaskDAOToTaskMapper;
import cz.ucl.logic.data.mappers.definitions.DAOToEntity.IUserDAOToUserMapper;

final public class TagDAOToTagMapper implements ITagDAOToTagMapper {

    public static final ITagDAOToTagMapper instance = new TagDAOToTagMapper();

    private final IUserDAOToUserMapper userDAOToUserMapper = UserDAOToUserMapper.instance;
    private final IColorDAOToColorMapper colorDAOToColorMapper = ColorDAOToColorMapper.instance;
    private final ITaskDAOToTaskMapper taskDAOToTaskMapper = TaskDAOToTaskMapper.instance;

    private TagDAOToTagMapper() {
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
                colorDAOToColorMapper.mapOrNull(v.getColor()),
                new ITask[0]
        );
    }
}
