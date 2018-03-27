package presentation.view;

import business.Facade;
import data_access.dto.Teacher;

import javax.swing.*;
import java.util.Collection;

class TeacherListView {
    private JList<Teacher> teacherList;
    private JPanel rootPanel;
    private final Facade facade;

    public TeacherListView(Facade facade) {
        this.facade = facade;
        populateCourseList();
    }

    public void populateCourseList() {
        Collection<Teacher> Teachers = facade.getTeachers();
        Teacher[] TeachersArray = Teachers.toArray(new Teacher[Teachers.size()]);
        teacherList.setListData(TeachersArray);
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public Teacher getSelectedTeacher() {
        return teacherList.getSelectedValue();
    }
}

