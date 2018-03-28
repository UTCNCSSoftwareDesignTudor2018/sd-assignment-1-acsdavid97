package presentation.controller;

import business.facade.CourseFacade;
import data_access.dto.Teacher;
import presentation.view.CourseEditView;
import presentation.view.CourseFormView;

import javax.swing.*;

class CourseEditController {
    private final CourseFacade facade;
    private final CourseEditView courseEditView;

    public CourseEditController(CourseEditView courseEditView) {
        this.facade = new CourseFacade();
        this.courseEditView = courseEditView;

        courseEditView.addAddActionListener(actionEvent -> {
            createCourseAddView(courseEditView);
        });

        courseEditView.addUpdateActionListener(actionEvent -> {
            // TODO: update course
        });

        courseEditView.addDeleteActionListener(actionEvent -> {
            // TODO: delete course
        });
    }

    private void createCourseAddView(CourseEditView courseEditView) {
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

