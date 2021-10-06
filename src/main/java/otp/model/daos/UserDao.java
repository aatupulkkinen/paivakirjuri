package otp.model.daos;

import otp.model.entities.User;

public interface UserDao {
    User get(String name, String password);
    Boolean insert(String name, String password);
    User changePassword(User user, String newPass);
}
