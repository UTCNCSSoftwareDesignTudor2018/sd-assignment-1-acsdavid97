package presentation.controller;

import business.Facade;
import business.UserBLL;
import data_access.dto.User;
import presentation.view.UserView;

public class UserController {
    private UserView userView;
    private Facade facade;
    private User user;

    public UserController(UserView userView, Facade facade, User user) {
        this.userView = userView;
        this.facade = facade;
        this.user = user;

        userView.setUpdateButtonListener(actionEvent -> {
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
