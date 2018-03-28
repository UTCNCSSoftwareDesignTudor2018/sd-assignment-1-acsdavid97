package presentation.view;

import business.facade.TeacherFacade;
import data_access.dto.Teacher;

import javax.swing.*;
import java.util.Collection;

class TeacherListView {
    private JList<Teacher> teacherList;
    private JPanel rootPanel;
    private final TeacherFacade facade;

    public TeacherListView() {
        this.facade = new TeacherFacade();
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

