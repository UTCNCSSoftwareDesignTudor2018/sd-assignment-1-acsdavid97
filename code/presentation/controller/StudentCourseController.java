package presentation.controller;

import presentation.view.StudentCourseView;

import java.awt.event.ActionListener;

public class StudentCourseController {

    private StudentCourseView studentCourseView;

    public StudentCourseController(StudentCourseView studentCourseView) {
        this.studentCourseView = studentCourseView;
    }

    public void setButtonListener(ActionListener actionListener) {
        studentCourseView.setButtonActionListener(actionListener);
    }
}
