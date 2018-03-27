package presentation.view;

import business.Facade;
import data_access.dto.Course;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Collection;

public class CourseListView {
    private JList<Course> courseList;
    private JPanel rootPanel;
    private JButton button;
    private final Facade facade;

    public CourseListView(Facade facade) {
        this.facade = facade;
        this.populateCourseList();
    }

    private void populateCourseList() {
        Collection<Course> courseCollection = facade.getCourses();
        Course[] courses = courseCollection.toArray(new Course[courseCollection.size()]);
        courseList.setListData(courses);
    }

    public Course getSelectedCourse() {
        return courseList.getSelectedValue();
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public void setButtonActionListener(ActionListener actionListener) {
        button.addActionListener(actionListener);
    }

    public void setButtonText(String text) {
        this.button.setText(text);
    }
}
