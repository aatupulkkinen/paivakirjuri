package otp.model.daos;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import otp.model.entities.User;

import static org.junit.jupiter.api.Assertions.*;

class UserLocalTest {

    private final UserDao userLocal = new UserLocal();

    @Test
    @Order(2)
    void get() {
        User user = userLocal.get("test", "123");
        assertNotNull(user);
        assertEquals(user.getName(), "test");
    }

    @Test
    @Order(1)
    void insert() {
        userLocal.insert(new User("test", "123", "123", "123"));
    }
}