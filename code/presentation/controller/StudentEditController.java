package presentation.controller;

import business.facade.StudentFacade;
import data_access.dto.*;
import presentation.view.*;

import javax.swing.*;
import java.sql.Timestamp;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

class StudentEditController {
    private final StudentFacade facade;
    private final StudentEditView studentEditView;

    public StudentEditController(StudentEditView studentEditView) {
        this.facade = new StudentFacade();
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
            createGradeView(studentEditView);
        });

        studentEditView.addGenerateReportActionListener(actionEvent -> {
            createGenerateWindow(studentEditView);
        });
    }

    private void createGenerateWindow(StudentEditView studentEditView) {
        JFrame generateWindow = new JFrame();

        GenerateReportForm generateReportForm = new GenerateReportForm();
        generateReportForm.setReportGenerateActionListener(action -> {
            Student student = studentEditView.getSelected();
            if (student == null) {
                return;
            }

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dateFrom = simpleDateFormat.parse(generateReportForm.getDateFromText(), new ParsePosition(0));
            if (dateFrom == null) {
                AlertHelper.displayError("invalid from date");
                return;
            }
            Timestamp timestampFrom = new Timestamp(dateFrom.getTime());

            Date dateTo = simpleDateFormat.parse(generateReportForm.getDateToText(), new ParsePosition(0));
            if (dateTo == null) {
                AlertHelper.displayError("invalid to date");
                return;
            }
            Timestamp timestampTo = new Timestamp(dateTo.getTime());

            Collection<Exam> exams = facade.findExamsForStudentForInterval(student, timestampFrom, timestampTo);
            generateReportForm.populateExamList(exams);
        });

        generateWindow.setContentPane(generateReportForm.getRootPanel());

        generateWindow.setVisible(true);
        generateWindow.pack();
        generateWindow.setLocationRelativeTo(null);
    }

    private void createGradeView(StudentEditView studentEditView) {
        Student student = studentEditView.getSelected();
        if (student == null) {
            return;
        }
        GradeStudentCourseView courseView = new GradeStudentCourseView(facade.findCoursesOfStudent(student));
        courseView.setGradeButtonActionListener(action -> {
            Course course = courseView.getSelected();
            if (course == null) {
                return;
            }
            if (facade.findExamOfStudentCourse(student, course) != null) {
                AlertHelper.displayError("student already graded");
                return;
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
    }

    private void createUserUpdateView(User user, Login login, Student student) {
        JFrame userUpdateView = new JFrame();
        UserView userView = new UserView(login, user);
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
        UserView userView = new UserView(login, user);
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
