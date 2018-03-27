package presentation.view;

import business.Facade;
import data_access.dto.Course;
import data_access.dto.Student;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Collection;

public class StudentCourseView {
    // todo: refactor
    private JList<Course> list1;
    private JButton examButton;
    private JButton button1;
    private JPanel rootPanel;

    public JPanel getRootPanel() {
        return this.rootPanel;
    }

    private final Facade facade;
    private final Student student;

    public StudentCourseView(Facade facade, Student student) {
        this.facade = facade;
        this.student = student;
        this.populateCourseList();
    }

    public void setButtonActionListener(ActionListener actionListener) {
        button1.addActionListener(actionListener);
    }

    public void setExamButtonActionListener(ActionListener actionListener) {
        examButton.addActionListener(actionListener);
    }

    public void populateCourseList() {
        Collection<Course> courseCollection = facade.findCoursesOfStudent(student);
        Course[] courses = courseCollection.toArray(new Course[courseCollection.size()]);
        list1.setListData(courses);
    }

    public Course getSelectedCourse() {
        return list1.getSelectedValue();
    }

    public void setButtonText(String text) {
        button1.setText(text);
    }

    public void setExamButtonText(String text) {
        examButton.setText(text);
    }
}
