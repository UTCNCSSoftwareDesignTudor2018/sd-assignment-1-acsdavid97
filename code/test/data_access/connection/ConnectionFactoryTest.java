package data_access.connection;

import data_access.RepositoryException;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

class ConnectionFactoryTest {

    @Test
    void testConnectionToDB() throws RepositoryException {
        Connection connection = ConnectionFactory.getConnection();
    }
}