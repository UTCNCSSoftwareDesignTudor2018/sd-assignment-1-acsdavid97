package presentation.controller;

import business.facade.LoginFacade;
import data_access.dto.Login;
import data_access.dto.Student;
import data_access.dto.Teacher;
import presentation.view.LoginView;
import presentation.view.StudentView;
import presentation.view.TeacherView;

public class LoginController {
    private final LoginView loginView;
    private final LoginFacade facade;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        this.facade = new LoginFacade();

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
            StudentView studentView = new StudentView(student);
            StudentController studentController = new StudentController(studentView, student);
        }

        Teacher teacher = facade.findTeacherByLogin(login);
        if (teacher != null) {
            TeacherView teacherView = new TeacherView();
            TeacherController teacherController = new TeacherController(teacherView);
        }
    }


}
