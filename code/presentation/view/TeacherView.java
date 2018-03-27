package presentation.view;

import business.Facade;

import javax.swing.*;

public class TeacherView extends JFrame{
    private JPanel rootPanel;
    private final Facade facade;
    private StudentEditView studentEditView;
    private TeacherEditView teacherEditView;
    private CourseEditView courseEditView;

    public TeacherView(Facade facade) {
        this.facade = facade;
    }

    private void createUIComponents() {
        rootPanel = new JPanel();
        rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.Y_AXIS));

        studentEditView = new StudentEditView(facade);
        rootPanel.add(studentEditView);

        teacherEditView = new TeacherEditView(facade);
        rootPanel.add(teacherEditView);

        courseEditView = new CourseEditView(facade);
        rootPanel.add(courseEditView);

        this.setContentPane(rootPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    public StudentEditView getStudentEditView() {
        return studentEditView;
    }

    public TeacherEditView getTeacherEditView() {
        return teacherEditView;
    }

    public CourseEditView getCourseEditView() {
        return courseEditView;
    }
}
