package otp.model.daos;

import org.junit.jupiter.api.Test;
import otp.model.encryption.EncryptionHandler;
import otp.model.entities.Code;
import otp.model.entities.User;
import otp.util.UserUtils;

import static org.junit.jupiter.api.Assertions.*;
import static otp.util.Utils.generateString;

class ForgotDaoImplTest {

    private final ForgotDaoImpl forgotDao = new ForgotDaoImpl();

    private final EncryptionHandler encryptionHandler = new EncryptionHandler();

    @Test
    void testInsertAndGet() {
        User user = UserUtils.getInstance().getUser();
        System.out.println(user.getPassword());
        System.out.println(user.getName());
        final String code = "EBk4bPiJoK";

        final String name = generateString();
        boolean result = forgotDao.insert(new Code(user.getName(), code));

        final Code code2 = forgotDao.get(encryptionHandler.decrypt(user.getName()));

        assertTrue(result);
        assertEquals(code2.getName(), user.getName());
        assertEquals(code2.getCode(), code);
    }


}