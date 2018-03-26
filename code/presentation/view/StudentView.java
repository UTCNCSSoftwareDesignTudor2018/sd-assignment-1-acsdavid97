package presentation.view;

import business.Facade;
import data_access.dto.Course;
import data_access.dto.Student;
import data_access.dto.User;

import javax.swing.*;
import java.awt.*;

public class StudentView extends JFrame{
    private JPanel rootPanel;
    private StudentCourseView studentCourseView;
    private UserView userView;
    private Facade facade;
    private Student student;

    public StudentView(Facade facade, Student student) {
        this.student = student;
        this.facade = facade;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        this.rootPanel = new JPanel();
        this.rootPanel.setLayout(new BorderLayout());

        this.studentCourseView = new StudentCourseView(this.facade, this.student);
        this.rootPanel.add(this.studentCourseView.getRootPanel(), BorderLayout.EAST);


        User user = facade.findUserByStudent(student);
        this.userView = new UserView(user, facade);
        this.userView.setPreferredSize(new Dimension(400, 400));
        rootPanel.add(userView.getRootPanel(), BorderLayout.WEST);

        this.setContentPane(rootPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    public StudentCourseView getStudentCourseView() {
        return studentCourseView;
    }

    public UserView getUserView() {
        return userView;
    }

    public Course getSelectedCourse() {
        return studentCourseView.getSelectedCourse();
    }
}
