package presentation.controller;

import presentation.view.CourseView;

import java.awt.event.ActionListener;

public class CourseController {

    private CourseView courseView;

    public CourseController(CourseView courseView) {
        this.courseView = courseView;
    }

    public void setButtonListener(ActionListener actionListener) {
        courseView.setButtonActionListener(actionListener);
    }
}
