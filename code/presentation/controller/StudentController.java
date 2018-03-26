package presentation.controller;

import business.Facade;
import data_access.dto.Course;
import data_access.dto.Student;
import data_access.dto.User;
import presentation.view.StudentView;

public class StudentController {
    private Student student;
    private StudentView studentView;
    private UserController userController;
    private StudentCourseController studentCourseController;
    private Facade facade;

    public StudentController(StudentView studentView, Student student, Facade facade) {
        this.student = student;
        this.studentView = studentView;
        this.facade = facade;

        User user = facade.findUserByStudent(student);
        this.userController = new UserController(studentView.getUserView(), facade, user);
        this.studentCourseController = new StudentCourseController(studentView.getStudentCourseView());
        this.studentCourseController.setButtonListener(actionEvent -> {
            // todo find course
            Course course = studentView.getSelectedCourse();
            if (course == null) {
                return;
            }
            if (!facade.enrollStudent(student, course)) {
                AlertHelper.displayError("student already enrolled");
            }
        });


    }


}
