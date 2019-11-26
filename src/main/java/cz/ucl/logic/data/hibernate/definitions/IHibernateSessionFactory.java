package cz.ucl.logic.data.hibernate.definitions;

import cz.ucl.logic.data.hibernate.OpenedSessionFunction;
import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

public interface IHibernateSessionFactory {

    void createSession(OpenedSessionFunction fn) throws PersistenceException;

}
