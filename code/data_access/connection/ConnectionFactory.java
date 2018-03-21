package data_access.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/tucn?useSSL=false&serverTimezone=UTC";
    private static final String USER = "user";
    private static final String PASSWORD = "Assignment1";

    private static final ConnectionFactory factory = new ConnectionFactory();

    private Connection createConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        return connection;
    }

    public static Connection getConnection() throws SQLException {
        return factory.createConnection();
    }
}
