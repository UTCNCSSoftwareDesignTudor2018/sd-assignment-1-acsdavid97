package data_access;

import data_access.dto.Login;
import data_access.dto.User;

public interface UserRepository extends GenericRepository<User>{

    User findUserByLogin(Login login) throws RepositoryException;
}
