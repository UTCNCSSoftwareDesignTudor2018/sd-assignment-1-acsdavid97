import business.Facade;
import presentation.controller.LoginController;
import presentation.view.LoginView;

class StudentManagementApplication {
    public static void main(String[] args) {
        Facade facade = new Facade();
        LoginView loginView = new LoginView();
        LoginController controller = new LoginController(loginView, facade);
    }
}
