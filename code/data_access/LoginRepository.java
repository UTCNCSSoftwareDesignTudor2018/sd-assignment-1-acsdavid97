package data_access;

import data_access.dto.Login;

public interface LoginRepository extends GenericRepository<Login>{

    Login findByUsername(String username) throws RepositoryException;
}
