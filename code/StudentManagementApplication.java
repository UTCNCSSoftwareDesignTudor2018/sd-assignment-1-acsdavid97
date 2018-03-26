import business.Facade;
import business.LoginBLL;
import presentation.controller.LoginController;
import presentation.view.LoginView;

import javax.swing.*;

public class StudentManagementApplication {
    public static void main(String[] args) {
        Facade facade = new Facade();
        LoginView loginView = new LoginView();
        LoginController controller = new LoginController(loginView, facade);
    }
}
