package cz.ucl.logic.data.mappers.definitions.DAOToEntity;

import cz.ucl.logic.app.entities.definition.IUser;
import cz.ucl.logic.data.dao.UserDAO;
import cz.ucl.logic.data.mappers.definitions.IMapper;

public interface IUserDAOToUserMapper extends IMapper<UserDAO, IUser> {
}
