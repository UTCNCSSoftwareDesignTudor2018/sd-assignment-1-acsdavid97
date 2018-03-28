package presentation.view;

import business.facade.CourseFacade;
import data_access.dto.Course;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CourseEditView extends JPanel {
    private final CourseFacade facade;
    private final JButton addButton;
    private final JButton deleteButton;
    private final JButton updateButton;
    private final CourseListView courseListView;

    public CourseEditView() {
        this. facade = new CourseFacade();
        this.setLayout(new BorderLayout());

        courseListView = new CourseListView(facade.getCourses());
        this.add(courseListView.getRootPanel(), BorderLayout.NORTH);

        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout());

        addButton = new JButton("Add");
        buttons.add(addButton);
        deleteButton = new JButton("Delete");
        buttons.add(deleteButton);
        updateButton = new JButton("Update");
        buttons.add(updateButton);

        this.add(buttons, BorderLayout.SOUTH);
    }

    public void addAddActionListener(ActionListener actionListener) {
        addButton.addActionListener(actionListener);
    }

    public void addDeleteActionListener(ActionListener actionListener) {
        deleteButton.addActionListener(actionListener);
    }

    public void addUpdateActionListener(ActionListener actionListener) {
        updateButton.addActionListener(actionListener);
    }

    public Course getSelected() {
        return courseListView.getSelectedCourse();
    }

    public void updateCourseList() {
        courseListView.populateCourseList(facade.getCourses());
    }
}

