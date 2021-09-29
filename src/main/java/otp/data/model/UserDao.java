package otp.data.model;

import otp.data.UsersEntity;

public interface UserDao {
    UsersEntity get(String name, String password);
}
