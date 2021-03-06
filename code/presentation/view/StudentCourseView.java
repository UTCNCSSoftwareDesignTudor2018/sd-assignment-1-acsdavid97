package presentation.view;

import data_access.dto.Course;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Collection;

public class StudentCourseView {
    private JButton unEnrollButton;
    private JButton viewGradeButton;
    private JPanel rootPanel;
    private CourseListView courseListView;

    public StudentCourseView(Collection<Course> courseCollection) {
        this.courseListView = new CourseListView(courseCollection);
        this.unEnrollButton = new JButton("un-enroll");
        this.viewGradeButton = new JButton("view grade");

        rootPanel = new JPanel();
        rootPanel.setLayout(new BorderLayout());
        rootPanel.add(courseListView.getRootPanel(), BorderLayout.NORTH);

        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));

        buttons.add(unEnrollButton);
        buttons.add(viewGradeButton);

        rootPanel.add(buttons, BorderLayout.SOUTH);
    }

    public void setUnEnrollButtonActionListener(ActionListener actionListener){
        this.unEnrollButton.addActionListener(actionListener);
    }

    public void setViewGradeButtonActionListener(ActionListener actionListener) {
        this.viewGradeButton.addActionListener(actionListener);
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
