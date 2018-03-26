package business;

import data_access.dto.*;

import java.util.ArrayList;
import java.util.Collection;

public class Facade {
    private CourseBLL courseBLL;
    private LoginBLL loginBLL;
    private UserBLL userBLL;
    private StudentBLL studentBLL;
    private TeacherBLL teacherBLL;
    private EnrollBLL enrollBLL;

    public Facade() {
        courseBLL = new CourseBLL();
        loginBLL = new LoginBLL();
        userBLL = new UserBLL();
        studentBLL = new StudentBLL();
        teacherBLL = new TeacherBLL();
        enrollBLL = new EnrollBLL();
    }

    public Collection<Course> getCourses() {
        return courseBLL.getCourses();
    }

    public boolean checkCredentials(String username, String password) {
        Login login = new Login(username, password);
        return loginBLL.checkCredentials(login);
    }

    public Login findLoginByUsername(String username) {
        return loginBLL.findLoginByUsername(username);
    }

    public void updateUser(User user) {
        userBLL.updateUser(user);
    }

    public Student findStudentByLogin(Login login) {
        User user = this.userBLL.findUserByLogin(login);
        if (user == null) {
            return null;
        }
        return studentBLL.findStudentByUser(user);
    }

    public User findUserByStudent(Student student) {
        return this.userBLL.findUserById(student.getUser_id());
    }

    public Teacher findTeacherByLogin(Login login) {
        User user = this.userBLL.findUserByLogin(login);
        if (user == null) {
            return null;
        }
        return teacherBLL.findTeacherByUser(user);
    }

    public Student findStudentById(int id) {
        return studentBLL.findStudentById(id);
    }

    public User findUserById(int id) {
        return userBLL.findUserById(id);
    }

    public boolean enrollStudent(Student student, Course course) {
        return enrollBLL.enrollStudent(student, course);
    }

    public Collection<Course> findCoursesOfStudent(Student student) {
        Collection<Enrollment> enrollments = enrollBLL.findEnrollmentsOfStudent(student);
        Collection<Course> courses = new ArrayList<>();
        for (Enrollment enrollment :
                enrollments) {
            Course course = courseBLL.findCourseById(enrollment.getCourse_id());
            courses.add(course);
        }
        return courses;
    }
}
