package presentation.view;

import javax.swing.*;

public class LoginView extends JFrame {
    private JPanel rootPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton button1;

    public LoginView() {
        this.setContentPane(rootPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JButton getButton1() {
        return button1;
    }

}
