package data_access;

import data_access.dto.Login;

public interface LoginRepositoryInterface extends GenericRepository<Login>{

    Login findByUsername(String username) throws RepositoryException;
}
