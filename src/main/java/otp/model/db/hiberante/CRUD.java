package otp.model.db.hiberante;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import otp.model.db.BlockInsideTransaction;

abstract public class CRUD {

    private final SessionFactory sf = HibernateUtility.getSessionFactoryHib();

    protected Session openSession() {
        return sf.openSession();
    }

    protected Object openWithTransaction(BlockInsideTransaction block) {
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
            e.printStackTrace();
            result = null;
            if (t != null)
                t.rollback();
        }
        return result;
    }
}
