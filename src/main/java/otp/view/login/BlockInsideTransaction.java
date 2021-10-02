package otp.view.login;

import org.hibernate.Session;

public interface BlockInsideTransaction {
    Object doOperation(Session session);
}
