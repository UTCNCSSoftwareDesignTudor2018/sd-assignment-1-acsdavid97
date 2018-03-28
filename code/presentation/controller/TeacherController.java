package presentation.controller;

import presentation.view.TeacherView;

class TeacherController {
    private final StudentEditController studentEditController;
    private final TeacherEditController teacherEditController;
    private CourseEditController courseEditController;

    public TeacherController(TeacherView teacherView) {
        studentEditController = new StudentEditController(teacherView.getStudentEditView());
        teacherEditController = new TeacherEditController(teacherView.getTeacherEditView());
        courseEditController = new CourseEditController(teacherView.getCourseEditView());
    }
}
