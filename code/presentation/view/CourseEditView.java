package presentation.view;

import business.Facade;
import data_access.dto.Course;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CourseEditView extends JPanel {
    private Facade facade;
    private JButton addButton;
    private JButton deleteButton;
    private JButton updateButton;
    private CourseListView courseListView;

    public CourseEditView(Facade facade) {
        this. facade = facade;
        this.setLayout(new BorderLayout());

        courseListView = new CourseListView(facade);
        this.add(courseListView.getRootPanel(), BorderLayout.NORTH);

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

    public Course getSelected() {
        return courseListView.getSelectedCourse();
    }
}

