package presentation.view;

import data_access.dto.Student;

import javax.swing.*;

class StudentInfoView {
    private JPanel rootPanel;
    private JLabel idLabel;
    private JLabel idShower;
    private final Student student;

    public StudentInfoView(Student student) {
        this.student = student;
        setIdText(student.getId_number());
    }

    private void setIdText(String text) {
        idShower.setText(text);
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
