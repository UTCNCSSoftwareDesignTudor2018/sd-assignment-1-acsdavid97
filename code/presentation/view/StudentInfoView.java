package presentation.view;

import data_access.dto.Student;

import javax.swing.*;

public class StudentInfoView {
    private JPanel rootPanel;
    private JLabel idLabel;
    private JLabel idShower;
    private Student student;

    public StudentInfoView(Student student) {
        this.student = student;
        setIdText(student.getId_number());
    }

    public void setIdText(String text) {
        idShower.setText(text);
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
