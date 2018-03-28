package business.facade;

import business.facade.process.TeacherBLL;
import business.facade.process.UserBLL;
import data_access.TeacherDatabase;
import data_access.UserDatabase;
import data_access.dto.Login;
import data_access.dto.Teacher;
import data_access.dto.User;

import java.util.Collection;

public class TeacherFacade {
    private UserBLL userBLL = new UserBLL(new UserDatabase());
    private TeacherBLL teacherBLL = new TeacherBLL(new TeacherDatabase());

    private LoginFacade loginFacade = new LoginFacade();

    public Login findLoginByTeacher(Teacher teacher) {
        User user = findUserByTeacher(teacher);
        if (user == null) {
            return null;
        }

        return loginFacade.findLoginByUser(user);
    }

    public User findUserByTeacher(Teacher teacher) {
        return userBLL.findUserById(teacher.getUser_id());
    }


    public void deleteLogin(Login login) {
        loginFacade.deleteLogin(login);
    }

    public boolean updateTeacher(Login newLogin, User newUser, Teacher newTeacher) {
        if (!loginFacade.updateLogin(newLogin)) {
            return false;
        }

        userBLL.updateUser(newUser);
        teacherBLL.updateTeacher(newTeacher);
        return true;
    }

    public boolean addTeacher(Login newLogin, User newUser, Teacher newTeacher) {
        if (!loginFacade.createLoginCredentials(newLogin.getUsername(), newLogin.getPassword())) {
            return false;
        }

        Login createdLogin = loginFacade.findLoginByUsername(newLogin.getUsername());
        newUser.setLogin_id(createdLogin.getId());
        userBLL.addUser(newUser);
        User createdUser = userBLL.findUserByLogin(createdLogin);
        newTeacher.setUser_id(createdUser.getId());
        teacherBLL.addTeacher(newTeacher);
        return true;
    }

    public Collection<Teacher> getTeachers() {
        return teacherBLL.getTeachers();
    }


}
