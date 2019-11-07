package cz.ucl;

import cz.ucl.logic.app.entities.User;
import cz.ucl.logic.app.entities.definition.IUser;
import cz.ucl.logic.data.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class Playground {

    public static void main(String[] args) {

        IUser user = new User("samuel@kodytek.cz", "samuel.kodytek", "hesloJeVeslo123");
        IUser user2 = new User("tomas@novy.cz", "tomas.novy", "pes");

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // save the users objects
            session.save(user);
            session.save(user2);

            // commit
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();

            if (transaction != null) {
                transaction.rollback();
            }
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<User> students = session.createQuery("from User", User.class).list();
            students.forEach(s -> System.out.println(s.getEmail()));
        } catch (Exception e) {
            e.printStackTrace();

            if (transaction != null) {
                transaction.rollback();
            }
        }

    }

}
