package presentation.controller;

import business.Facade;
import data_access.dto.Course;
import data_access.dto.Login;
import data_access.dto.Student;
import data_access.dto.User;
import presentation.view.StudentCourseView;
import presentation.view.StudentEditView;
import presentation.view.UserView;

import javax.swing.*;

class StudentEditController {
    private final Facade facade;
    private final StudentEditView studentEditView;

    public StudentEditController(Facade facade, StudentEditView studentEditView) {
        this.facade = facade;
        this.studentEditView = studentEditView;

        studentEditView.addAddActionListener(actionEvent -> {
            createUserAddView(new User(), new Login());
        });

        studentEditView.addUpdateActionListener(actionEvent -> {
            Student student = studentEditView.getSelected();
            if (student == null) {
                return;
            }
            Login login = facade.findLoginByStudent(student);
            User user = facade.findUserByStudent(student);
            createUserUpdateView(user, login, student);
        });

        studentEditView.addDeleteActionListener(actionEvent -> {
            Student student = studentEditView.getSelected();
            if (student == null) {
                return;
            }
            Login login = facade.findLoginByStudent(student);
            facade.deleteLogin(login);
            studentEditView.updateStudentList();
        });

        studentEditView.addGradeActionListener(actionEvent -> {
            Student student = studentEditView.getSelected();
            if (student == null) {
                return;
            }
            StudentCourseView courseView = new StudentCourseView(facade, student);
            courseView.setExamButtonText("grade");
            courseView.setButtonActionListener(action -> {
                Course course = courseView.getSelectedCourse();
                if (course == null) {
                    return;
                }
                if (facade.findExamOfStudentCourse(student, course) != null) {
                    AlertHelper.displayError("student already graded");
                }
                String gradeString = JOptionPane.showInputDialog("enter grade");
                if (gradeString == null){
                    return;
                }
                int grade;
                try {
                    grade = Integer.parseInt(gradeString);
                }catch (NumberFormatException e) {
                    AlertHelper.displayError("please enter a number");
                    return;
                }
                facade.gradeStudent(student, course, grade);
            });
            JFrame courseViewWindow = new JFrame();
            courseViewWindow.setContentPane(courseView.getRootPanel());
            courseViewWindow.pack();
            courseViewWindow.setVisible(true);
            courseViewWindow.setLocationRelativeTo(null);
        });

        studentEditView.addGenerateReportActionListener(actionEvent -> {
            // todo;
        });
    }

    private void createUserUpdateView(User user, Login login, Student student) {
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
            Student newStudent = new Student(student);
            if(!facade.updateStudent(newLogin, newUser, newStudent)) {
                AlertHelper.displayError("username already taken");
            }
            studentEditView.updateStudentList();
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
            Student newStudent = new Student();
            newStudent.setId_number("replace");
            if(!facade.addStudent(newLogin, newUser, newStudent)) {
                AlertHelper.displayError("username already taken");
            }
            studentEditView.updateStudentList();
            userAddView.setVisible(false);
            userAddView.dispose();
        });

        userAddView.pack();
        userAddView.setVisible(true);
        userAddView.setLocationRelativeTo(null);
    }
}
