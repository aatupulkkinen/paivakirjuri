package otp.data.db;



import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DBConnectionTest {

    @Test
    public void testConnectionToDatabase() {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select 1");
            rs.first();
            int testInt = rs.getInt(1);
            assertEquals(testInt, 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
