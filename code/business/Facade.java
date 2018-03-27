package business;

import data_access.dto.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

public class Facade {
    private CourseBLL courseBLL;
    private LoginBLL loginBLL;
    private UserBLL userBLL;
    private StudentBLL studentBLL;
    private TeacherBLL teacherBLL;
    private EnrollBLL enrollBLL;
    private ExamBLL examBLL;

    public Facade() {
        courseBLL = new CourseBLL();
        loginBLL = new LoginBLL();
        userBLL = new UserBLL();
        studentBLL = new StudentBLL();
        teacherBLL = new TeacherBLL();
        enrollBLL = new EnrollBLL();
        examBLL = new ExamBLL();
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

    public Collection<Student> getStudents() {
        return studentBLL.getStudents();
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

    public boolean unenrollStudent(Student student, Course course) {
        return enrollBLL.unenrollStudent(student, course);
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

    public Exam findExamOfStudentCourse(Student student, Course course) {
        Enrollment enrollment = enrollBLL.findEnrollmentByStudentAndCourse(student, course);
        if (enrollment == null) {
            return null;
        }
        return findExamOfEnrollment(enrollment);
    }

    public Exam findExamOfEnrollment(Enrollment enrollment) {
        return examBLL.findExamFromEnrollment(enrollment);
    }

    public Collection<Teacher> getTeachers() {
        return teacherBLL.getTeachers();
    }

    public boolean updateLogin(Login updatedLogin) {
        return loginBLL.updateLogin(updatedLogin);
    }

    public Login findLoginByUser(User user) {
        return loginBLL.findLoginByUser(user);
    }

    public boolean addStudent(Login newLogin, User newUser, Student newStudent) {
        if (!loginBLL.createLoginCredentials(newLogin.getUsername(), newLogin.getPassword())) {
            return false;
        }

        Login createdLogin = loginBLL.findLoginByUsername(newLogin.getUsername());
        newUser.setLogin_id(createdLogin.getId());
        userBLL.addUser(newUser);
        User createdUser = userBLL.findUserByLogin(createdLogin);
        newStudent.setUser_id(createdUser.getId());
        studentBLL.addStudent(newStudent);
        return true;
    }

    public Login findLoginByStudent(Student student) {
        User user = findUserByStudent(student);
        if (user == null) {
            return null;
        }
        return findLoginByUser(user);
    }

    public void deleteLogin(Login login) {
        loginBLL.deleteLogin(login);
    }

    public boolean updateStudent(Login newLogin, User newUser, Student newStudent) {
        if (!loginBLL.updateLogin(newLogin)){
            return false;
        }

        userBLL.updateUser(newUser);
        studentBLL.updateStudent(newStudent);
        return true;
    }

    public Login findLoginByTeacher(Teacher teacher) {
        User user = findUserByTeacher(teacher);
        if (user == null) {
            return null;
        }

        return findLoginByUser(user);
    }

    public User findUserByTeacher(Teacher teacher) {
        return userBLL.findUserById(teacher.getUser_id());
    }

    public boolean updateTeacher(Login newLogin, User newUser, Teacher newTeacher) {
        if (!loginBLL.updateLogin(newLogin)) {
            return false;
        }

        userBLL.updateUser(newUser);
        teacherBLL.updateTeacher(newTeacher);
        return true;
    }

    public boolean addTeacher(Login newLogin, User newUser, Teacher newTeacher) {
        if (!loginBLL.createLoginCredentials(newLogin.getUsername(), newLogin.getPassword())) {
            return false;
        }

        Login createdLogin = loginBLL.findLoginByUsername(newLogin.getUsername());
        newUser.setLogin_id(createdLogin.getId());
        userBLL.addUser(newUser);
        User createdUser = userBLL.findUserByLogin(createdLogin);
        newTeacher.setUser_id(createdUser.getId());
        teacherBLL.addTeacher(newTeacher);
        return true;
    }

    public void gradeStudent(Student student, Course course, int grade) {
        Enrollment enrollment = enrollBLL.findEnrollmentByStudentAndCourse(student, course);
        Exam exam = new Exam();
        exam.setDate(new Timestamp(System.currentTimeMillis()));
        exam.setEnrollment_id(enrollment.getId());
        examBLL.addGrade(exam);
    }
}
