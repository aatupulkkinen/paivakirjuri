package otp.model.daos;

import otp.model.entities.User;

public interface UserDao {
    User get(String name, String password);

    Boolean insert(User user);

    User changePassword(User user, String newPass);
    User get(String name);
}
