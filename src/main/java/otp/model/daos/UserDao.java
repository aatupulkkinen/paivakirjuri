package otp.model.daos;

import otp.model.entities.User;

public interface UserDao {
    User get(String name, String password);
}