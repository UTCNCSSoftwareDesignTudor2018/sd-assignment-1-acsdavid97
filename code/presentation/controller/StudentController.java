package presentation.controller;

import business.Facade;
import data_access.dto.*;
import presentation.view.CourseListView;
import presentation.view.StudentCourseView;
import presentation.view.StudentView;

class StudentController {
    private final Student student;
    private final StudentView studentView;
    private final UserController userController;
    private final Facade facade;

    public StudentController(StudentView studentView, Student student, Facade facade) {
        this.student = student;
        this.studentView = studentView;
        this.facade = facade;

        User user = facade.findUserByStudent(student);
        Login login = facade.findLoginByUser(user);
        this.userController = new UserController(studentView.getUserView(), facade, user, login);

        CourseListView courseListView = this.studentView.getCourseListView();
        courseListView.setButtonActionListener(actionEvent -> {
            Course course = studentView.getSelectedCourse();
            if (course == null) {
                return;
            }
            if (!facade.enrollStudent(student, course)) {
                AlertHelper.displayError("student already enrolled");
            }
            studentView.getStudentCourseView().populateCourseList();
        });


        StudentCourseView studentCourseView = this.studentView.getStudentCourseView();
        studentCourseView.setButtonActionListener(actionEvent -> {
            Course course = studentView.getSelectedStudentCourse();
            if (course == null) {
                return;
            }
            if (!facade.unEnrollStudent(student, course)) {
                AlertHelper.displayError("could not un-enroll student");
            }
            studentView.getStudentCourseView().populateCourseList();
        });

        studentCourseView.setExamButtonActionListener(actionEvent ->{
            Course course = studentView.getSelectedStudentCourse();
            if (course == null) {
                return;
            }
            Exam exam = facade.findExamOfStudentCourse(student, course);
            if (exam == null) {
                AlertHelper.displayInfo("no grade found");
            }
            else{
                AlertHelper.displayInfo(exam.toString());
            }
        });

    }


}
