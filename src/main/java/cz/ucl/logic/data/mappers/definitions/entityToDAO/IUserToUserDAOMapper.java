package cz.ucl.logic.data.mappers.definitions.entityToDAO;

import cz.ucl.logic.app.entities.definition.IUser;
import cz.ucl.logic.data.dao.UserDAO;
import cz.ucl.logic.data.mappers.definitions.IMapper;

public interface IUserToUserDAOMapper extends IMapper<IUser, UserDAO> {
}
