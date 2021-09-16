package data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testUser(){
        User user = new User();
        String name = "Nimi";
        user.setName(name);
        assertEquals(user.getName(), name);
    }
}