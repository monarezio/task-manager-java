package cz.ucl.logic.app.services;

import cz.ucl.logic.app.entities.User;
import cz.ucl.logic.app.entities.definition.IUser;
import cz.ucl.logic.app.services.definition.IUserService;
import cz.ucl.logic.app.validators.definition.IValidator;
import cz.ucl.logic.data.managers.definitions.IUserManager;
import cz.ucl.logic.exceptions.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// TODO: Implement this
public class UserService implements IUserService {

    private IUser currentUser = null;

    private final IUserManager userManager;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final IValidator validator;

    public UserService(IUserManager userManager, BCryptPasswordEncoder bCryptPasswordEncoder, IValidator validator) {
        this.userManager = userManager;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.validator = validator;
    }

    @Override
    public void loginUser(String email, String password) throws AlreadyLoggedInException, InvalidCredentialsException, InvalidPropertyException {
        if (isUserLoggedIn())
            throw new AlreadyLoggedInException(getUserLoggedIn().getUsername() + "Is already logged in!");

        validator.validateFields(new User(email), "email");

        IUser user = userManager.getByEmail(email);
        if (user == null || !bCryptPasswordEncoder.matches(password, user.getPassword()))
            throw new InvalidCredentialsException("Invalid combination");

        currentUser = user;
    }

    @Override
    public void logoutUser() throws NotLoggedInException {
        if (!isUserLoggedIn()) throw new NotLoggedInException("No user is logged in!");

        currentUser = null;
    }

    @Override
    public void registerUser(String email, String username, String password) throws EmailAddressAlreadyUsedException, InvalidPropertyException {
        User user = new User(email, username, bCryptPasswordEncoder.encode(password));
        validator.validateAll(user);
        userManager.addUser(user);
    }

    @Override
    public boolean isUserLoggedIn() {
        return currentUser != null;
    }

    @Override
    public IUser getUserLoggedIn() {
        return currentUser;
    }

    @Override
    public void destroyUserLoggedIn() throws NotLoggedInException {
        if (currentUser == null)
            throw new NotLoggedInException("User is not logged in");
        currentUser = null;
    }
}
