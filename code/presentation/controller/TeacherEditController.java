package presentation.controller;

import business.Facade;
import data_access.dto.Login;
import data_access.dto.Teacher;
import data_access.dto.User;
import presentation.view.TeacherEditView;
import presentation.view.UserView;

import javax.swing.*;

public class TeacherEditController {
    private Facade facade;
    private TeacherEditView teacherEditView;

    public TeacherEditController(Facade facade, TeacherEditView teacherEditView) {
        this.facade = facade;
        this.teacherEditView = teacherEditView;

        teacherEditView.addAddActionListener(actionEvent -> {
            createUserAddView(new User(), new Login());
        });

        teacherEditView.addUpdateActionListener(actionEvent -> {
            Teacher teacher = teacherEditView.getSelected();
            if (teacher == null) {
                return;
            }
            Login login = facade.findLoginByTeacher(teacher);
            User user = facade.findUserByTeacher(teacher);
            createUserUpdateView(user, login, teacher);
        });

        teacherEditView.addDeleteActionListener(actionEvent -> {
            Teacher teacher = teacherEditView.getSelected();
            if (teacher == null) {
                return;
            }
            Login login = facade.findLoginByTeacher(teacher);
            facade.deleteLogin(login);
            teacherEditView.updateTeacherList();
        });
    }

    private void createUserUpdateView(User user, Login login, Teacher teacher) {
        JFrame userUpdateView = new JFrame();
        UserView userView = new UserView(login, user, facade);
        userView.updateFields();
        userUpdateView.setContentPane(userView.getRootPanel());
        userView.setUpdateButtonListener(actionEvent -> {

            Login newLogin = new Login(login);
            newLogin.setUsername(userView.getUsernameText());
            newLogin.setPassword(userView.getPasswordText());
            User newUser = new User(user);
            newUser.setName(userView.getNameText());
            newUser.setPersonal_numerical_code(userView.getPersonalNumCodeText());
            newUser.setCard_number(userView.getCardNumberText());
            newUser.setAddress(userView.getAddressText());
            // todo add group
            Teacher newTeacher = new Teacher(teacher);
            if(!facade.updateTeacher(newLogin, newUser, newTeacher)) {
                AlertHelper.displayError("username already taken");
            }
            teacherEditView.updateTeacherList();
            userUpdateView.setVisible(false);
            userUpdateView.dispose();
        });

        userUpdateView.pack();
        userUpdateView.setVisible(true);
        userUpdateView.setLocationRelativeTo(null);
    }

    private void createUserAddView(User user, Login login) {
        JFrame userAddView = new JFrame();
        UserView userView = new UserView(login, user, facade);
        userAddView.setContentPane(userView.getRootPanel());
        userView.setUpdateButtonListener(actionEvent -> {
            Login newLogin = new Login(userView.getUsernameText(), userView.getPasswordText());
            User newUser = new User();
            newUser.setName(userView.getNameText());
            newUser.setPersonal_numerical_code(userView.getPersonalNumCodeText());
            newUser.setCard_number(userView.getCardNumberText());
            newUser.setAddress(userView.getAddressText());
            // todo add group
            Teacher newTeacher = new Teacher();
            if(!facade.addTeacher(newLogin, newUser, newTeacher)) {
                AlertHelper.displayError("username already taken");
            }
            teacherEditView.updateTeacherList();
            userAddView.setVisible(false);
            userAddView.dispose();
        });

        userAddView.pack();
        userAddView.setVisible(true);
        userAddView.setLocationRelativeTo(null);
    }
}
