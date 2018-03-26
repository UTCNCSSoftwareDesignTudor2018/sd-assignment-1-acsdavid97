package business;

import data_access.LoginRepository;

public class Login {

    private LoginRepository loginRepository;

    public Login() {
        loginRepository = new LoginRepository();
    }

    public boolean checkCredentials(String username, String password) {
        loginRepository.findByUsername(username);
    }
}
