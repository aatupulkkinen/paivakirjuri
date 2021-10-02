package otp.view.login;

import otp.data.UsersEntity;
import otp.data.hiberante.CRUD;
import otp.data.model.UserDao;

public class UserDaoImpl extends CRUD implements UserDao {

    @Override
    public UsersEntity get(String name, String password) {
        UsersEntity user;

        user = (UsersEntity) openWithTransaction(
                (session) -> session.createQuery("from UsersEntity where name = :n and password = :p")
                        .setParameter("n", name)
                        .setParameter("p", password)
                        .list()
                        .get(0)
        );

        return user;
    }
}
