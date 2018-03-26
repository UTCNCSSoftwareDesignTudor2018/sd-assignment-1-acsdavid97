package presentation.view;

import business.Facade;
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
    private User user;
    private Facade facade;

    public UserView(User user, Facade facade) {
        this.user = user;
        this.facade = facade;
        this.updateFields();
    }

    public void setUpdateButtonListener(ActionListener actionListener) {
        updateButton.addActionListener(actionListener);
    }

    public void updateFields() {
        this.user = facade.findUserById(user.getId());

        this.nameField.setText(user.getName());
        this.cardNumberField.setText(user.getCard_number());
        this.personalNumericalCodeField.setText(user.getPersonal_numerical_code());
        this.addressField.setText(user.getAddress());
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
}
