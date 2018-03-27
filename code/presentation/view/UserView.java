package presentation.view;

import business.Facade;
import data_access.dto.Login;
import data_access.dto.User;

import javax.swing.*;
import java.awt.event.ActionListener;

public class UserView extends JPanel{
    private JTextField nameField;
    private JTextField cardNumberField;
    private JTextField personalNumericalCodeField;
    private JTextField addressField;
    private JButton updateButton;
    private JPanel rootPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private User user;
    private Login login;
    private final Facade facade;

    public UserView(Login login, User user, Facade facade) {
        this.login = login;
        this.user = user;
        this.facade = facade;
    }

    public void setUpdateButtonListener(ActionListener actionListener) {
        updateButton.addActionListener(actionListener);
    }

    public void updateFields() {
        this.user = facade.findUserById(user.getId());
        this.login = facade.findLoginByUser(user);

        this.nameField.setText(user.getName());
        this.cardNumberField.setText(user.getCard_number());
        this.personalNumericalCodeField.setText(user.getPersonal_numerical_code());
        this.addressField.setText(user.getAddress());

        this.usernameField.setText(login.getUsername());
        this.passwordField.setText(login.getPassword());
    }

    public String getNameText () {
        return nameField.getText();
    }

    public String getCardNumberText () {
        return cardNumberField.getText();
    }

    public String getPersonalNumCodeText () {
        return personalNumericalCodeField.getText();
    }

    public String getAddressText () {
        return addressField.getText();
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public String getUsernameText() {
        return usernameField.getText();
    }

    public String getPasswordText() {
        return new String(passwordField.getPassword());
    }
}
