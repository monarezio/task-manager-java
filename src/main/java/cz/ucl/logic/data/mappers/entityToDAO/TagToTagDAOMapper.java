package cz.ucl.logic.data.mappers.entityToDAO;

import cz.ucl.logic.app.entities.definition.ITag;
import cz.ucl.logic.data.dao.TagDAO;
import cz.ucl.logic.data.mappers.definitions.IMapper;
import cz.ucl.logic.data.mappers.definitions.entityToDAO.IColorToColorDAOMapper;
import cz.ucl.logic.data.mappers.definitions.entityToDAO.ITagToTagDAOMapper;
import cz.ucl.logic.data.mappers.factory.IMapperFactory;

final public class TagToTagDAOMapper implements ITagToTagDAOMapper {

    private final IMapperFactory factory;

    public TagToTagDAOMapper(IMapperFactory factory) {
        this.factory = factory;
    }

    @Override
    public TagDAO mapOrNull(ITag v) {
        if (v == null) return null;

        TagDAO tagDAO = new TagDAO(v.getId());
        tagDAO.setTitle(v.getTitle());
        tagDAO.setColor(factory.getColorToColorDaoMapper().mapOrNull(v.getColor()));

        return tagDAO;
    }
}
