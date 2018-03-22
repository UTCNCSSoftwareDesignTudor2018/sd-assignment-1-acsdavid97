package data_access.connection;

import data_access.RepositoryException;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

class ConnectionFactoryTest {

    @Test
    void testConnectionToDB() throws RepositoryException {
        Connection connection = ConnectionFactory.getConnection();
    }
}