package data_access;

import data_access.dto.Login;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DatabaseRepositoryTest {

    private DatabaseRepository<Login> LoginRepository;

    @BeforeEach
    void setUp() {
        LoginRepository = new LoginRepository();
    }

    @Test
    void findById() {
    }

    @Test
    void add() throws RepositoryException {
        Login login = new Login();
        LoginRepository.add(login);
    }

    @Test
    void getAll() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}