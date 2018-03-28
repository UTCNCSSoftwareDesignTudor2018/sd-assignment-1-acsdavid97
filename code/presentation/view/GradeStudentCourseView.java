package presentation.view;

import data_access.dto.Course;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Collection;

public class GradeStudentCourseView {
    private JButton gradeButton;
    private JPanel rootPanel;
    private CourseListView courseListView;

    public GradeStudentCourseView(Collection<Course> courseCollection) {
        this.courseListView = new CourseListView(courseCollection);
        this.gradeButton = new JButton("grade");

        rootPanel = new JPanel();
        rootPanel.setLayout(new BorderLayout());
        rootPanel.add(courseListView.getRootPanel(), BorderLayout.NORTH);

        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));

        buttons.add(gradeButton);

        rootPanel.add(buttons, BorderLayout.SOUTH);
    }

    public void setGradeButtonActionListener(ActionListener actionListener) {
        this.gradeButton.addActionListener(actionListener);
    }

    public void populateCourseView(Collection<Course> courseCollection) {
        courseListView.populateCourseList(courseCollection);
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public Course getSelected() {
        return courseListView.getSelectedCourse();
    }
}
