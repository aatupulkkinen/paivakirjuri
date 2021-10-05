package otp.model.daos;

import org.junit.jupiter.api.RepeatedTest;
import otp.model.entities.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// todo make temp in-memory db
class UserDaoImplTest {

    private final UserDaoImpl userDaoRemote = new UserDaoImpl();

    @RepeatedTest(5)
    void testInsertAndGetUser() {
        final String name = generateString();
        final String password = generateString();
        Boolean result = userDaoRemote.insert(name, password);

        final User user = userDaoRemote.get(name, password);

        assertTrue(result);
        assertEquals(user.getName(), name);
        assertEquals(user.getPassword(), password);
    }

    private String generateString() {
        return java.util.UUID.randomUUID().toString();
    }

}