package cz.ucl.logic.data.mappers.definitions.entityToDAO;

import cz.ucl.logic.app.entities.Tag;
import cz.ucl.logic.app.entities.definition.ITag;
import cz.ucl.logic.data.dao.TagDAO;
import cz.ucl.logic.data.mappers.definitions.IMapper;

public interface ITagToTagDAOMapper extends IMapper<ITag, TagDAO> {
}
