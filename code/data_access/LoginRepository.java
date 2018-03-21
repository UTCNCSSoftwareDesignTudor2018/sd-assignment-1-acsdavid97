package data_access;

import data_access.dto.Login;

public class LoginRepository extends DatabaseRepository<Login>{
    public LoginRepository() {
        super(Login.class);
    }
}
