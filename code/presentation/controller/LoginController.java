package presentation.controller;

import business.Facade;
import data_access.dto.Login;
import data_access.dto.Student;
import data_access.dto.Teacher;
import presentation.view.LoginView;
import presentation.view.StudentView;
import presentation.view.TeacherView;

public class LoginController {
    private final LoginView loginView;
    private final Facade facade;

    public LoginController(LoginView loginView, Facade facade) {
        this.loginView = loginView;
        this.facade = facade;

        loginView.getButton1().addActionListener(actionEvent -> {
            String username = loginView.getUsernameField().getText();
            String password = new String(loginView.getPasswordField().getPassword());

            if (facade.checkCredentials(username, password)){
                loginView.setVisible(false);
                Login login = facade.findLoginByUsername(username);
                loginUser(login);
            }
            else{
                AlertHelper.displayError("Invalid login");
            }
        });
    }

    private void loginUser(Login login) {
        Student student = facade.findStudentByLogin(login);
        if (student != null) {
            StudentView studentView = new StudentView(this.facade, student);
            StudentController studentController = new StudentController(studentView, student, this.facade);
        }

        Teacher teacher = facade.findTeacherByLogin(login);
        if (teacher != null) {
            TeacherView teacherView = new TeacherView(this.facade);
            TeacherController teacherController = new TeacherController(facade, teacherView);
        }
    }


}
