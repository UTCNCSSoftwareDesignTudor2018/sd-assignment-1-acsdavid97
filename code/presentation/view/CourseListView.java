package presentation.view;

import data_access.dto.Course;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

public class CourseListView {
    private JList<Course> courseList;
    private JPanel rootPanel;

    public CourseListView(Collection<Course> courseCollection) {
        this.populateCourseList(courseCollection);
        rootPanel.setPreferredSize(new Dimension(200, 200));
    }

    public void populateCourseList(Collection<Course> courseCollection) {
        Course[] courses = courseCollection.toArray(new Course[courseCollection.size()]);
        courseList.setListData(courses);
    }

    public Course getSelectedCourse() {
        return courseList.getSelectedValue();
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
