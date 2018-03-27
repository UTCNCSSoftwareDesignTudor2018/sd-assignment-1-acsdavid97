package presentation.controller;

import business.Facade;
import presentation.view.TeacherView;

class TeacherController {
    private final StudentEditController studentEditController;
    private final TeacherEditController teacherEditController;
    private CourseEditController courseEditController;

    public TeacherController(Facade facade, TeacherView teacherView) {
        studentEditController = new StudentEditController(facade, teacherView.getStudentEditView());
        teacherEditController = new TeacherEditController(facade, teacherView.getTeacherEditView());
        courseEditController = new CourseEditController(facade, teacherView.getCourseEditView());
    }
}
