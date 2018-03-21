package data_access.connection;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

class ConnectionFactoryTest {

    @Test
    void testConnectionToDB() throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
    }
}