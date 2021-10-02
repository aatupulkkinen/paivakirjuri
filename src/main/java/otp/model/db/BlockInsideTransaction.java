package otp.model.db;

import org.hibernate.Session;

public interface BlockInsideTransaction {
    Object doOperation(Session session);
}
