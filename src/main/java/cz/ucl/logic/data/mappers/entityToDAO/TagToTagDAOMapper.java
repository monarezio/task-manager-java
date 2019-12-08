package cz.ucl.logic.data.mappers.entityToDAO;

import cz.ucl.logic.app.entities.definition.ITag;
import cz.ucl.logic.data.dao.TagDAO;
import cz.ucl.logic.data.mappers.definitions.entityToDAO.IColorToColorDAOMapper;
import cz.ucl.logic.data.mappers.definitions.entityToDAO.ITagToTagDAOMapper;

public class TagToTagDAOMapper implements ITagToTagDAOMapper {

    public final static ITagToTagDAOMapper instance = new TagToTagDAOMapper();

    private final IColorToColorDAOMapper colorToColorDAOMapper = ColorToColorDAOMapper.instance;

    @Override
    public TagDAO mapOrNull(ITag v) {
        if (v == null) return null;

        TagDAO tagDAO = new TagDAO(v.getId());
        tagDAO.setTitle(v.getTitle());
        tagDAO.setColor(colorToColorDAOMapper.mapOrNull(v.getColor()));

        return tagDAO;
    }
}
