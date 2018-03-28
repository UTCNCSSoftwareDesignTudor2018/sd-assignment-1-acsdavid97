package data_access;

import data_access.dto.Login;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

class DatabaseRepositoryTest {

    private DatabaseRepository<Login> loginRepository;

    @BeforeEach
    void setUp() {
        loginRepository = new LoginDatabase();
    }

    @Test
    void findById() throws RepositoryException {
        Login login = loginRepository.findById(1);
        System.out.println(login);
    }

    @Test
    void add() throws RepositoryException {
        Login login = new Login();
        login.setUsername("hello");
        login.setPassword("world");
        loginRepository.add(login);
    }

    @Test
    void getAll() throws RepositoryException {
        Collection<Login> logins = loginRepository.getAll();
        for (Login login : logins) {
            System.out.println(login);
        }
    }

    @Test
    void update() throws RepositoryException {
        Login login = new Login();
        login.setId(1);
        login.setUsername("whoareyou");
        login.setPassword("asdfljkllasfd");
        loginRepository.update(login);
        Login updatedLogin = loginRepository.findById(1);
        Assertions.assertTrue(updatedLogin.equals(login));
    }

    @Test
    void delete() throws RepositoryException {
        loginRepository.delete(1);
        Assertions.assertTrue(loginRepository.findById(1) == null);
    }
}