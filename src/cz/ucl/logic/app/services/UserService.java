package cz.ucl.logic.app.services;

import cz.ucl.logic.app.entities.definition.IUser;
import cz.ucl.logic.app.services.definition.IUserService;
import cz.ucl.logic.exceptions.AlreadyLoggedInException;
import cz.ucl.logic.exceptions.EmailAddressAlreadyUsedException;
import cz.ucl.logic.exceptions.InvalidCredentialsException;
import cz.ucl.logic.exceptions.NotLoggedInException;

// TODO: Implement this
public class UserService implements IUserService {

    @Override
    public void loginUser(String email, String password) throws AlreadyLoggedInException, InvalidCredentialsException {

    }

    @Override
    public void logoutUser() throws NotLoggedInException {

    }

    @Override
    public void registerUser(String email, String username, String password) throws EmailAddressAlreadyUsedException {

    }

    @Override
    public boolean isUserLoggedIn() {
        return false;
    }

    @Override
    public IUser getUserLoggedIn() {
        return null;
    }

    @Override
    public void destroyUserLoggedIn() throws NotLoggedInException {

    }
}
