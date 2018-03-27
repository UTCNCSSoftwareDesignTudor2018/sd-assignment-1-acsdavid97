package presentation.view;

import business.Facade;
import data_access.dto.Course;
import data_access.dto.Login;
import data_access.dto.Student;
import data_access.dto.User;

import javax.swing.*;
import java.awt.*;

public class StudentView extends JFrame{
    private JPanel rootPanel;
    private StudentCourseView studentCourseView;
    private CourseListView courseListView;
    private UserView userView;
    private final Facade facade;
    private final Student student;

    public StudentView(Facade facade, Student student) {
        this.student = student;
        this.facade = facade;
    }

    private void createUIComponents() {
        this.rootPanel = new JPanel();
        this.rootPanel.setLayout(new BorderLayout());

        this.studentCourseView = new StudentCourseView(this.facade, this.student);
        this.studentCourseView.setButtonText("un-enroll");
        this.studentCourseView.setExamButtonText("view grade");
        this.rootPanel.add(this.studentCourseView.getRootPanel(), BorderLayout.SOUTH);

        JPanel userStudentInfo = new JPanel();
        userStudentInfo.setLayout(new BorderLayout());

        User user = facade.findUserByStudent(student);
        Login login = facade.findLoginByUser(user);
        this.userView = new UserView(login, user, facade);
        userView.updateFields();
        this.userView.setPreferredSize(new Dimension(400, 400));
        userStudentInfo.add(userView.getRootPanel(), BorderLayout.SOUTH);

        StudentInfoView studentInfoView = new StudentInfoView(student);
        userStudentInfo.add(studentInfoView.getRootPanel(), BorderLayout.NORTH);

        rootPanel.add(userStudentInfo, BorderLayout.NORTH);

        this.courseListView = new CourseListView(facade);
        this.courseListView.setButtonText("enroll");
        rootPanel.add(courseListView.getRootPanel(), BorderLayout.CENTER);

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

    public Course getSelectedStudentCourse() {
        return studentCourseView.getSelectedCourse();
    }

    public Course getSelectedCourse() {
        return courseListView.getSelectedCourse();
    }

    public CourseListView getCourseListView() {
        return courseListView;
    }
}
