package business;

import data_access.LoginRepository;
import data_access.dto.Login;

public class LoginBLL {

    private LoginRepository loginRepository;

    public LoginBLL() {
        loginRepository = new LoginRepository();
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
}
