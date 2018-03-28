package presentation.controller;

import business.Facade;
import data_access.dto.Teacher;
import presentation.view.CourseEditView;
import presentation.view.CourseFormView;

import javax.swing.*;

class CourseEditController {
    private final Facade facade;
    private final CourseEditView courseEditView;

    public CourseEditController(Facade facade, CourseEditView courseEditView) {
        this.facade = facade;
        this.courseEditView = courseEditView;

        courseEditView.addAddActionListener(actionEvent -> {
            createCourseAddView(facade, courseEditView);
        });

        courseEditView.addUpdateActionListener(actionEvent -> {
            // TODO: update course
        });

        courseEditView.addDeleteActionListener(actionEvent -> {
            // TODO: delete course
        });
    }

    private void createCourseAddView(Facade facade, CourseEditView courseEditView) {
        JFrame courseWindow = new JFrame();
        CourseFormView courseFormView = new CourseFormView();
        courseWindow.setContentPane(courseFormView.getRootPanel());

        courseFormView.setButtonActionListener(action -> {
            String courseName = courseFormView.getCourseNameText();
            String teacherUsername = courseFormView.getTeacherUsernameText();
            Teacher teacher = facade.findTeacherByUsername(teacherUsername);
            if (teacher == null) {
                AlertHelper.displayError("invalid teacher username");
                return;
            }

            facade.addCourse(courseName, teacher);
            courseEditView.updateCourseList();
            courseWindow.setVisible(false);
            courseWindow.dispose();
        });

        courseWindow.setVisible(true);
        courseWindow.pack();
        courseWindow.setLocationRelativeTo(null);
    }
}

