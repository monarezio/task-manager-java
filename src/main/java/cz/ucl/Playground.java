package cz.ucl;

import cz.ucl.logic.data.dao.UserDAO;
import cz.ucl.logic.data.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class Playground {

    public static void main(String[] args) {

        UserDAO user = new UserDAO();
        user.setUsername("samuel");
        user.setEmail("samuel@kodytek.cz");
        user.setPassword("hesloJeVeslo");

        UserDAO user2 = new UserDAO();
        user2.setUsername("tomas");
        user2.setEmail("tomas@gmail.com");
        user2.setPassword("password");

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
            List<UserDAO> students = session.createQuery("from UserDAO", UserDAO.class).list();
            students.forEach(s -> System.out.println(s.getEmail()));
        } catch (Exception e) {
            e.printStackTrace();

            if (transaction != null) {
                transaction.rollback();
            }
        }

    }

}
