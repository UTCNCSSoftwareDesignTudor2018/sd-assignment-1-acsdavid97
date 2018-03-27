package presentation.view;

import business.Facade;
import data_access.dto.Student;

import javax.swing.*;
import java.util.Collection;

class StudentListView {
    private JList<Student> studentList;
    private JPanel rootPanel;
    private final Facade facade;

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
