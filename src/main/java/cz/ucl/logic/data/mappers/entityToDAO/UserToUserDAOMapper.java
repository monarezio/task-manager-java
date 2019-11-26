package cz.ucl.logic.data.mappers.entityToDAO;

import cz.ucl.logic.app.entities.definition.IUser;
import cz.ucl.logic.data.dao.UserDAO;
import cz.ucl.logic.data.mappers.definitions.entityToDAO.IUserToUserDAOMapper;

final public class UserToUserDAOMapper implements IUserToUserDAOMapper {

    public static IUserToUserDAOMapper instance = new UserToUserDAOMapper();

    private UserToUserDAOMapper() {
    }

    /**
     * @param v
     * @return if `v` null returns null
     */
    @Override
    public UserDAO mapOrNull(IUser v) {
        if (v == null) return null;

        UserDAO user = new UserDAO();

        user.setEmail(v.getEmail());
        user.setUsername(v.getUsername());
        user.setPassword(v.getPassword());

        /* TODO: it is'nt now needed but maybe I should implement mappers for Categroies, Tasks and Tags.  */

        return user;
    }
}
