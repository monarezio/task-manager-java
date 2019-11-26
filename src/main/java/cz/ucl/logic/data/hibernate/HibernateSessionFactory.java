package cz.ucl.logic.data.hibernate;

import cz.ucl.logic.data.hibernate.definitions.IHibernateSessionFactory;
import cz.ucl.logic.exceptions.InvalidCredentialsException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.util.Optional;

public class HibernateSessionFactory implements IHibernateSessionFactory {

    private SessionFactory sessionFactory;

    public void createSession(OpenedSessionFunction fn) throws PersistenceException {
        Transaction transaction = null;
        try (Session session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            fn.invoke(session);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            try {
                if (transaction != null) transaction.rollback();
            } catch (IllegalStateException ignored) {
            }

            throw e;
        }
    }

    private SessionFactory getSessionFactory() {
        if (sessionFactory == null) {

            // configures settings from hibernate.cfg.xml
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure() // configures settings from hibernate.cfg.xml
                    .build();

            try {
                sessionFactory = new MetadataSources(registry)
                        .buildMetadata()
                        .buildSessionFactory();
            } catch (Exception e) {
                e.printStackTrace();
                StandardServiceRegistryBuilder.destroy(registry);
            }
        }
        return sessionFactory;
    }

}
