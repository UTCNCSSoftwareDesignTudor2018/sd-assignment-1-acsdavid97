package presentation.controller;

import business.Facade;
import presentation.view.TeacherView;

public class TeacherController {
    private StudentEditController studentEditController;
    private TeacherEditController teacherEditController;
    private CourseEditController courseEditController;

    public TeacherController(Facade facade, TeacherView teacherView) {
        studentEditController = new StudentEditController(facade, teacherView.getStudentEditView());
        teacherEditController = new TeacherEditController(facade, teacherView.getTeacherEditView());
    }
}
