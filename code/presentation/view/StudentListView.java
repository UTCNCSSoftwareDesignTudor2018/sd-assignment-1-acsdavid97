package presentation.view;

import business.Facade;
import data_access.dto.Student;

import javax.swing.*;
import java.util.Collection;

public class StudentListView {
    private JList<Student> studentList;
    private JPanel rootPanel;
    private Facade facade;

    public StudentListView(Facade facade) {
        this.facade = facade;
        populateCourseList();
    }

    public void populateCourseList() {
        Collection<Student> students = facade.getStudents();
        Student[] studentsArray = students.toArray(new Student[students.size()]);
        studentList.setListData(studentsArray);
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public Student getSelectedStudent() {
        return studentList.getSelectedValue();
    }
}
