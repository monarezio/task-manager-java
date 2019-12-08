package cz.ucl.logic.data.managers.definitions;

import cz.ucl.logic.app.entities.definition.IUser;
import cz.ucl.logic.exceptions.EmailAddressAlreadyUsedException;
import cz.ucl.logic.exceptions.InvalidCredentialsException;

public interface IUserManager {

    IUser getByEmail(String email) throws InvalidCredentialsException;

    void addUser(IUser user) throws EmailAddressAlreadyUsedException;

    void addUserWithoutMockData(IUser user) throws EmailAddressAlreadyUsedException;

    void updateUser(long userId, String username, String email) throws EmailAddressAlreadyUsedException;

    void updatePassword(long userId, String password);

    void destroy(long userId);

}
