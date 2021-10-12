package otp.model.daos;

import otp.model.db.hiberante.CRUD;
import otp.model.encryption.EncryptionHandler;
import otp.model.entities.Code;

public class ForgotDaoImpl extends CRUD implements ForgotDao {

    private final EncryptionHandler encryptionHandler = new EncryptionHandler();

    @Override
    public Code get(String username) {
        String encName = encryptionHandler.encrypt(username);
        Code code;
        code = (Code) openWithTransaction(
                (session) -> session.createQuery("from Code where name = :n")
                        .setParameter("n", encName)
                        .list()
                        .get(0)
        );
        return code;
    }

    @Override
    public boolean insert(Code code) {
        Object result = openWithTransaction((session) -> {
            session.save(code);
            return true;
        });
        return result != null;
    }
}
