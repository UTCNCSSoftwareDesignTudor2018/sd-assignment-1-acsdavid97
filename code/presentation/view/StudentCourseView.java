package presentation.view;

import business.CourseBLL;
import business.Facade;
import data_access.dto.Course;
import data_access.dto.Student;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Collection;

public class CourseView{
    private JList<Course> list1;
    private JButton button1;
    private JPanel rootPanel;

    public JPanel getRootPanel() {
        return this.rootPanel;
    }

    private Facade facade;
    private Student student;

    public CourseView(Facade facade, Student student) {
        this.facade = facade;
        this.student = student;
        this.populateCourseList();
    }

    public void setButtonActionListener(ActionListener actionListener) {
        button1.addActionListener(actionListener);
    }

    public void populateCourseList() {
        Collection<Course> courseCollection = facade.findCoursesOfStudent(student);
        Course[] courses = courseCollection.toArray(new Course[courseCollection.size()]);
        list1.setListData(courses);
    }
}
