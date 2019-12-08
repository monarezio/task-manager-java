package cz.ucl.logic.data.managers;

import cz.ucl.logic.app.entities.definition.IUser;
import cz.ucl.logic.data.dao.UserDAO;
import cz.ucl.logic.data.hibernate.definitions.IHibernateSessionFactory;
import cz.ucl.logic.data.managers.definitions.IUserManager;
import cz.ucl.logic.data.mappers.definitions.DAOToEntity.IUserDAOToUserMapper;
import cz.ucl.logic.data.mappers.definitions.entityToDAO.IUserToUserDAOMapper;
import cz.ucl.logic.exceptions.EmailAddressAlreadyUsedException;
import cz.ucl.logic.exceptions.InvalidCredentialsException;
import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.util.concurrent.atomic.AtomicReference;

public class UserManager implements IUserManager {

    private final IUserDAOToUserMapper userDAOToUserMapper;
    private final IHibernateSessionFactory hibernateUtil;
    private final IUserToUserDAOMapper userToUserDAOMapper;

    public UserManager(IUserDAOToUserMapper userDAOToUserMapper, IUserToUserDAOMapper userToUserDAOMapper, IHibernateSessionFactory hibernateUtil) {
        this.userDAOToUserMapper = userDAOToUserMapper;
        this.userToUserDAOMapper = userToUserDAOMapper;
        this.hibernateUtil = hibernateUtil;
    }

    public IUser getByEmail(String email) throws InvalidCredentialsException {
        try {
            AtomicReference<UserDAO> result = new AtomicReference<>();
            hibernateUtil.createSession(i -> {
                result.set(i.createQuery("from UserDAO where email LIKE ?0", UserDAO.class)
                        .setParameter(0, email)
                        .getSingleResult()
                );
            });
            return userDAOToUserMapper.mapOrNull(result.get());
        } catch (NoResultException e) {
            throw new InvalidCredentialsException("Invalid combination");
        }
    }

    public void addUser(IUser user) throws EmailAddressAlreadyUsedException {
        try {
            hibernateUtil.createSession(i -> {
                UserDAO newUser = userToUserDAOMapper.mapOrNull(user);
                i.save(newUser);
            });
        } catch (PersistenceException e) {
            throw new EmailAddressAlreadyUsedException("Email " + user.getEmail() + " already in use!");
        }
    }

}
