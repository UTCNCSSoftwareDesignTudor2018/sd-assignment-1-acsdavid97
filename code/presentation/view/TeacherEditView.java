package presentation.view;

import business.Facade;
import data_access.dto.Teacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TeacherEditView extends JPanel{
    private TeacherListView teacherListView;
    private Facade facade;
    private JButton addButton;
    private JButton deleteButton;
    private JButton updateButton;

    public TeacherEditView(Facade facade) {
        this. facade = facade;
        this.setLayout(new BorderLayout());

        teacherListView = new TeacherListView(facade);
        this.add(teacherListView.getRootPanel(), BorderLayout.NORTH);

        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout());

        addButton = new JButton("Add");
        buttons.add(addButton);
        deleteButton = new JButton("Delete");
        buttons.add(deleteButton);
        updateButton = new JButton("Update");
        buttons.add(updateButton);

        this.add(buttons, BorderLayout.SOUTH);
    }

    public void addAddActionListener(ActionListener actionListener) {
        addButton.addActionListener(actionListener);
    }

    public void addDeleteActionListener(ActionListener actionListener) {
        deleteButton.addActionListener(actionListener);
    }

    public void addUpdateActionListener(ActionListener actionListener) {
        updateButton.addActionListener(actionListener);
    }

    public Teacher getSelected() {
        return teacherListView.getSelectedTeacher();
    }

    public void updateTeacherList() {
        teacherListView.populateCourseList();
    }

}
