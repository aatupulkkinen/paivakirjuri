package otp.model.daos;

import otp.model.entities.User;
import otp.model.db.hiberante.CRUD;

public class UserDaoImpl extends CRUD implements UserDao {

    @Override
    public User get(String name, String password) {
        User user;

        user = (User) openWithTransaction(
                (session) -> session.createQuery("from User where name = :n and password = :p")
                        .setParameter("n", name)
                        .setParameter("p", password)
                        .list()
                        .get(0)
        );

        return user;
    }
}
