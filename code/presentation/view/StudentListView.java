package presentation.view;

import business.facade.StudentFacade;
import data_access.dto.Student;

import javax.swing.*;
import java.util.Collection;

class StudentListView {
    private JList<Student> studentList;
    private JPanel rootPanel;
    private final StudentFacade facade;

    public StudentListView() {
        this.facade = new StudentFacade();
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
