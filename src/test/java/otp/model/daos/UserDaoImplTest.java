package otp.model.daos;

import org.junit.jupiter.api.RepeatedTest;
import otp.model.encryption.EncryptionHandler;
import otp.model.entities.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static otp.util.Utils.generateString;

// todo make temp in-memory db
class UserDaoImplTest {

    private final UserDaoImpl userDaoRemote = new UserDaoImpl();

    private final EncryptionHandler encryptionHandler = new EncryptionHandler();

    @RepeatedTest(5)
    void testInsertAndGetUser() {
        final String name = generateString();
        final String password = generateString();
        Boolean result = userDaoRemote.insert(new User(name, password, "123", "123"));

        final User user = userDaoRemote.get(name, password);

        assertTrue(result);
        assertEquals(encryptionHandler.decrypt(user.getName()), name);
        assertEquals(encryptionHandler.decrypt(user.getPassword()), password);
    }

}