package business.facade;

import business.facade.process.LoginBLL;
import business.facade.process.StudentBLL;
import business.facade.process.TeacherBLL;
import business.facade.process.UserBLL;
import data_access.LoginDatabase;
import data_access.StudentDatabase;
import data_access.TeacherDatabase;
import data_access.UserDatabase;
import data_access.dto.Login;
import data_access.dto.Student;
import data_access.dto.Teacher;
import data_access.dto.User;

public class LoginFacade {
    private UserBLL userBLL = new UserBLL(new UserDatabase());
    private LoginBLL loginBLL = new LoginBLL(new LoginDatabase());
    private StudentBLL studentBLL = new StudentBLL(new StudentDatabase());
    private TeacherBLL teacherBLL = new TeacherBLL(new TeacherDatabase());

    public Student findStudentByLogin(Login login) {
        User user = this.userBLL.findUserByLogin(login);
        if (user == null) {
            return null;
        }
        return studentBLL.findStudentByUser(user);
    }

    public Teacher findTeacherByLogin(Login login) {
        User user = this.userBLL.findUserByLogin(login);
        if (user == null) {
            return null;
        }
        return teacherBLL.findTeacherByUser(user);
    }

    public Login findLoginByUser(User user) {
        return loginBLL.findLoginByUser(user);
    }

    public boolean checkCredentials(String username, String password) {
        Login login = new Login(username, password);
        return loginBLL.checkCredentials(login);
    }

    public Login findLoginByUsername(String username) {
        return loginBLL.findLoginByUsername(username);
    }

    public void deleteLogin(Login login) {
        loginBLL.deleteLogin(login);
    }

    public boolean updateLogin(Login updatedLogin) {
        return loginBLL.updateLogin(updatedLogin);
    }


    public boolean createLoginCredentials(String username, String password) {
        return loginBLL.createLoginCredentials(username, password);
    }
}
