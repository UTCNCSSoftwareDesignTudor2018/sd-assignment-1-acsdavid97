package presentation.view;

import business.Facade;
import data_access.dto.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StudentEditView extends JPanel {
    private final StudentListView studentListView;
    private final Facade facade;
    private final JButton addButton;
    private final JButton deleteButton;
    private final JButton updateButton;
    private final JButton gradeButton;
    private final JButton generateReportButton;

    public StudentEditView(Facade facade) {
        this. facade = facade;
        this.setLayout(new BorderLayout());

        studentListView = new StudentListView(facade);
        this.add(studentListView.getRootPanel(), BorderLayout.NORTH);

        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout());

        addButton = new JButton("Add");
        buttons.add(addButton);
        deleteButton = new JButton("Delete");
        buttons.add(deleteButton);
        updateButton = new JButton("Update");
        buttons.add(updateButton);
        gradeButton = new JButton("Grade");
        buttons.add(gradeButton);
        generateReportButton = new JButton("Generate report");
        buttons.add(generateReportButton);

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

    public void addGradeActionListener(ActionListener actionListener) {
        gradeButton.addActionListener(actionListener);
    }

    public void addGenerateReportActionListener(ActionListener actionListener) {
        generateReportButton.addActionListener(actionListener);
    }

    public Student getSelected() {
        return studentListView.getSelectedStudent();
    }

    public void updateStudentList() {
        studentListView.populateCourseList();
    }
}

