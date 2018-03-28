package business.facade;

import business.facade.process.UserBLL;
import data_access.UserDatabase;
import data_access.dto.Login;
import data_access.dto.User;

public class UserFacade {
    private UserBLL userBLL = new UserBLL(new UserDatabase());

    private LoginFacade loginFacade = new LoginFacade();

    public User findUserById(int id) {
        return userBLL.findUserById(id);
    }

    public Login findLoginByUser(User user) {
        return loginFacade.findLoginByUser(user);
    }

    public void updateUser(User user) {
        userBLL.updateUser(user);
    }

    public boolean updateLogin(Login updatedLogin) {
        return loginFacade.updateLogin(updatedLogin);
    }
}
