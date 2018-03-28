import presentation.controller.LoginController;
import presentation.view.LoginView;

class StudentManagementApplication {
    public static void main(String[] args) {
        LoginView loginView = new LoginView();
        LoginController controller = new LoginController(loginView);
    }
}
