package presentation.controller;

import business.Facade;
import presentation.view.CourseEditView;

class CourseEditController {
    private final Facade facade;
    private final CourseEditView courseEditView;

    public CourseEditController(Facade facade, CourseEditView courseEditView) {
        this.facade = facade;
        this.courseEditView = courseEditView;

        courseEditView.addAddActionListener(actionEvent -> {
            // TODO: add course
        });

        courseEditView.addUpdateActionListener(actionEvent -> {
            // TODO: update course
        });

        courseEditView.addDeleteActionListener(actionEvent -> {
            // TODO: delete course
        });
    }
}

