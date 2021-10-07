package otp.util;

import otp.model.daos.UserDao;
import otp.model.daos.UserDaoImpl;
import otp.model.daos.UserLocal;
import otp.model.entities.User;

public class UserUtils {

    private static final UserUtils userUtils = new UserUtils();

    public static UserUtils getInstance() {
        return userUtils;
    }

    private final UserDao userDao = new UserDaoImpl();
    private final UserDao local = new UserLocal();

    private User commonUser = null;

    private UserUtils() {

    }

    private void createUser() {
        final String name = Utils.generateString();
        final String password = Utils.generateString();
        User user = new User(name, password, "123", "123");
        if (userDao.insert(user)){
            commonUser = userDao.get(name, password);
            local.insert(user);
        }
    }

    public User getUser(){
        createUser();
        return commonUser;
    }
}
