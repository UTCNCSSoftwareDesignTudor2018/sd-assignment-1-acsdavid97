package presentation.controller;

import business.Facade;
import data_access.dto.Login;
import data_access.dto.User;
import presentation.view.UserView;

class UserController {
    private final UserView userView;
    private final Facade facade;
    private User user;
    private Login login;

    public UserController(UserView userView, Facade facade, User user, Login login) {
        this.userView = userView;
        this.facade = facade;
        this.user = user;
        this.login = login;

        userView.setUpdateButtonListener(actionEvent -> {
            Login updatedLogin = new Login(login);
            updatedLogin.setPassword(userView.getPasswordText());
            updatedLogin.setUsername(userView.getUsernameText());

            if (!facade.updateLogin(updatedLogin)) {
                AlertHelper.displayError("username already taken");
                return;
            }else{
                this.login = updatedLogin;
            }

            User updatedUser = new User(user);
            updatedUser.setAddress(userView.getAddressText());
            updatedUser.setCard_number(userView.getCardNumberText());
            updatedUser.setName(userView.getNameText());
            updatedUser.setPersonal_numerical_code(userView.getPersonalNumCodeText());
            facade.updateUser(updatedUser);
            this.user = updatedUser;

        });
    }
}
