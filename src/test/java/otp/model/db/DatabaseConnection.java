package otp.model.db;

import java.sql.*;

public class DatabaseConnection {

    private static final DatabaseConnection databaseConnection = new DatabaseConnection();
    private Connection connection;
    // todo: use user with permissions scoped to one db
    private static final String URL = "jdbc:mariadb://10.114.34.5:3306/test?user=admin&password=k|jV]8@=[F|i";

    public static DatabaseConnection getInstance() {
        return databaseConnection;
    }

    private DatabaseConnection() {
        connection = initConnection();
    }

    public Connection getConnection() {
        return connection;
    }

    private Connection initConnection() {
        try {
            connection = DriverManager.getConnection(URL);
            System.out.println("Connected to database");
            return connection;
        } catch (SQLException e) {
            do {
                e.printStackTrace();
            } while (e.getNextException() != null);
            System.exit(-1);
            return null;
        }
    }
}