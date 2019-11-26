package cz.ucl.logic.data.hibernate;

import org.hibernate.Session;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

public interface OpenedSessionFunction {
    void invoke(Session session) throws PersistenceException;
}
