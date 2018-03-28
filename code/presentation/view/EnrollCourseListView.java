package presentation.view;

import data_access.dto.Course;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Collection;

public class EnrollCourseListView {
    private CourseListView courseListView;
    private JPanel rootPanel;
    private JButton enrollButton;

    public EnrollCourseListView(Collection<Course> courseCollection) {
        rootPanel = new JPanel();
        rootPanel.setLayout(new BorderLayout());

        this.courseListView = new CourseListView(courseCollection);
        rootPanel.add(courseListView.getRootPanel(), BorderLayout.NORTH);
        enrollButton = new JButton("enroll");
        rootPanel.add(enrollButton, BorderLayout.SOUTH);
    }

    public Course getSelectedCourse() {
        return courseListView.getSelectedCourse();
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public void setButtonActionListener(ActionListener actionListener) {
        enrollButton.addActionListener(actionListener);
    }
}
