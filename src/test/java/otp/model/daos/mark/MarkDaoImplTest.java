package otp.model.daos.mark;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import otp.model.entities.Mark;
import otp.model.entities.User;
import otp.util.UserUtils;


import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MarkDaoImplTest {

    private final MarkDao markDao = new MarkDaoImpl();
    private final User user = UserUtils.getInstance().getUser();

    @Test
    void testInsertAndGet() {
        Mark mark = new Mark();
        mark.setName(user.getName());
        mark.setColor("#212133");
        mark.setContent("this is content");
        mark.setCreated(new Date(System.currentTimeMillis()));
        boolean result = markDao.insert(mark);
        assertTrue(result);

        List<Mark> marks = markDao.getAll(user.getName());
        assertEquals(1, marks.size());
    }


}