package otp.model.daos;

import otp.model.encryption.EncryptionHandler;
import otp.model.entities.User;
import otp.model.db.hiberante.CRUD;

public class UserDaoImpl extends CRUD implements UserDao {

    private final EncryptionHandler encryptionHandler = new EncryptionHandler();

    @Override
    public User get(String name, String password) {
        User user;
        String encName = encryptionHandler.encrypt(name);
        String encPassword = encryptionHandler.encrypt(password);
        user = (User) openWithTransaction(
                (session) -> session.createQuery("from User where name = :n and password = :p")
                        .setParameter("n", encName)
                        .setParameter("p", encPassword)
                        .list()
                        .get(0)
        );
        return user;
    }

    @Override
    public User get(String name) {
        String encName = encryptionHandler.encrypt(name);
        User user;
        user = (User) openWithTransaction(
                (session) -> session.createQuery("from User where name = :n")
                        .setParameter("n", encName)
                        .list()
                        .get(0)
        );
        return user;
    }


    @Override
    public Boolean insert(User user) {
        String encName = encryptionHandler.encrypt(user.getName());
        String encPass = encryptionHandler.encrypt(user.getPassword());
        user.setPassword(encPass);
        user.setName(encName);
        Object result = openWithTransaction((session) -> {
            session.save(user);
            return true;
        });
        return result != null;
    }

    @Override
    public User changePassword(User user, String newPass) {
        String encPass = encryptionHandler.encrypt(newPass);
        Object result = openWithTransaction((session) -> {
            user.setPassword(encPass);
            session.update(user);
            return user;
        });
        try {
            return (User) result;
        } catch (ClassCastException ee) {
            ee.printStackTrace();
            return null;
        }
    }
}
