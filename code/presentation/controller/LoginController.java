package presentation.controller;

import business.Facade;
import data_access.dto.Login;
import data_access.dto.Student;
import data_access.dto.Teacher;
import presentation.view.LoginView;
import presentation.view.StudentView;

public class LoginController {
    private LoginView loginView;
    private Facade facade;

    public LoginController(LoginView loginView, Facade facade) {
        this.loginView = loginView;
        this.facade = facade;

        loginView.getButton1().addActionListener(actionEvent -> {
            String username = loginView.getTextField1().getText();
            String password = new String(loginView.getPasswordField1().getPassword());

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
//            StudentCourseView courseView = new StudentCourseView(facade);
//            StudentCourseController courseController = new StudentCourseController(courseView);
        }

        Teacher teacher = facade.findTeacherByLogin(login);
        if (teacher != null) {
            //TeacherView teacherView = new Teacher(Viewthis.facade, teacher);
        }
    }


}
