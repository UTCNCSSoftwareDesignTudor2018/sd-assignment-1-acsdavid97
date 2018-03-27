package business;

import data_access.LoginDatabase;
import data_access.LoginRepository;
import data_access.dto.Login;
import data_access.dto.User;

public class LoginBLL {

    private LoginRepository loginRepository;

    LoginBLL(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public boolean checkCredentials(Login login) {
        Login loginFound = loginRepository.findByUsername(login.getUsername());
        if (loginFound == null) {
            return false;
        }

        return loginFound.getPassword().equals(login.getPassword());
    }

    public boolean createLoginCredentials(String username, String password) {
        Login login = loginRepository.findByUsername(username);
        if (login != null) {
            return false;
        }
        loginRepository.add(new Login(username, password));
        return true;
    }

    public Login findLoginByUsername(String username) {
        return loginRepository.findByUsername(username);
    }

    public boolean updateLogin(Login updatedLogin) {
        Login login = loginRepository.findByUsername(updatedLogin.getUsername());
        if (login != null && login.getId() != updatedLogin.getId()) {
            return false;
        }
        loginRepository.update(updatedLogin);
        return true;
    }

    public Login findLoginByUser(User user) {
        return loginRepository.findById(user.getLogin_id());
    }

    public void deleteLogin(Login login) {
        loginRepository.delete(login);
    }
}
