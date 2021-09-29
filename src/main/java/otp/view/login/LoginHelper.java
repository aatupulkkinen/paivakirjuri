package otp.view.login;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import otp.data.UsersEntity;
import otp.data.hiberante.HibernateUtility;
import otp.data.model.UserDao;

interface FunctionInsideTransaction {
    Object doOperation(Session session);
}

public class LoginHelper implements UserDao {

    public LoginHelper() {
    }

    private Session openSession() {
        SessionFactory sf = HibernateUtility.getSessionFactoryHib();
        System.out.println(sf);

        return HibernateUtility.getSessionFactoryHib().openSession();
    }

    private Object openWithTransaction(FunctionInsideTransaction block) {
        Transaction t = null;
        Object result;
        try {
            Session session = openSession();
            t = session.beginTransaction();
            result = block.doOperation(session);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            System.out.println("Virhe");
            result = null;
            if (t != null)
                t.rollback();
        }
        return result;
    }

    @Override
    public UsersEntity get(String name, String password) {
        UsersEntity user;

        user = (UsersEntity) openWithTransaction((session) -> session.createQuery("from UsersEntity where name = :n and password = :p")
                .setParameter("n", name)
                .setParameter("p", password)
                .list().get(0));

        return user;
    }
}
