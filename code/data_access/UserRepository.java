package data_access;

import data_access.dto.User;

public class UserRepository extends DatabaseRepository<User>{
    protected UserRepository() {
        super(User.class);
    }
}
