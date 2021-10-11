package otp.model.daos;

import org.junit.jupiter.api.RepeatedTest;
import otp.model.entities.Code;
import otp.model.entities.User;
import otp.util.UserUtils;

import static org.junit.jupiter.api.Assertions.*;
import static otp.util.Utils.generateString;

class ForgotDaoImplTest {

    private final ForgotDaoImpl forgotDao = new ForgotDaoImpl();


    @RepeatedTest(5)
    void testInsertAndGetUser() {
        User user = UserUtils.getInstance().getUser();
        final String code = "1234567891012";

        final String name = generateString();
        boolean result = forgotDao.insert(new Code(user.getName(), code));

        final Code code2 = forgotDao.get(user.getName());

        assertTrue(result);
        assertEquals(code2.getName(), user.getName());
        assertEquals(code2.getCode(), code);
    }


}