package presentation.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CourseFormView {
    private JPanel rootPanel;
    private JTextField courseName;
    private JTextField teacherUsernameField;
    private JButton button;

    public void setButtonActionListener(ActionListener actionListener) {
        button.addActionListener(actionListener);
    }

    public String getCourseNameText() {
        return courseName.getText();
    }

    public String getTeacherUsernameText() {
        return teacherUsernameField.getText();
    }

    public void setButtonText(String text) {
        button.setText(text);
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
