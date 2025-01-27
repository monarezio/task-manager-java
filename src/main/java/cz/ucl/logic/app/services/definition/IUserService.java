package cz.ucl.logic.app.services.definition;

import cz.ucl.logic.app.entities.definition.IUser;
import cz.ucl.logic.exceptions.*;

/**
 * This interface describes a class which should contain all application logic related to user
 * management, manipulation and authentication
 */
public interface IUserService {
    /**
     * Allows user to login (AppLogin will remember which user is logged in)
     */
    void loginUser(String email, String password) throws AlreadyLoggedInException, InvalidCredentialsException, InvalidPropertyException;

    /**
     * Allows user to logout from the system
     */
    void logoutUser() throws NotLoggedInException;

    /**
     * Allows user to register into the system
     * This method will not login the registered user. After registration, user has to login by the loginUser(...) method
     */
    void registerUser(String email, String username, String password) throws EmailAddressAlreadyUsedException, InvalidPropertyException;

    void registerUserWithoutMockData(String email, String username, String password) throws EmailAddressAlreadyUsedException, InvalidPropertyException;

    /**
     * Returns true if there is a user logged into the system, false othewise
     * When this method returns true, the userLoggedIn() method should not return null
     */
    boolean isUserLoggedIn();

    /**
     * Returns the logged in user or null
     */
    IUser getUserLoggedIn();

    /**
     * Destroys a logged in, registered user
     */
    void destroyUserLoggedIn() throws NotLoggedInException;

    void updateUser(String username, String email) throws EmailAddressAlreadyUsedException, InvalidPropertyException;

    void updatePassword(String password);
}
