package business;

import data_access.*;
import data_access.dto.*;

import java.lang.reflect.ParameterizedType;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

public class Facade {
    private final CourseBLL courseBLL;
    private final LoginBLL loginBLL;
    private final UserBLL userBLL;
    private final StudentBLL studentBLL;
    private final TeacherBLL teacherBLL;
    private final EnrollBLL enrollBLL;
    private final ExamBLL examBLL;

    public Facade() {
        courseBLL = new CourseBLL(new CourseDatabase());
        loginBLL = new LoginBLL(new LoginDatabase());
        userBLL = new UserBLL(new UserDatabase());
        studentBLL = new StudentBLL(new StudentDatabase());
        teacherBLL = new TeacherBLL(new TeacherDatabase());
        enrollBLL = new EnrollBLL(new EnrollmentDatabase());
        examBLL = new ExamBLL(new ExamDatabase());
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

    public Teacher findTeacherByUsername(String username) {
        Login login = findLoginByUsername(username);
        if (login == null) {
            return null;
        }
        return findTeacherByLogin(login);
    }

    public User findUserById(int id) {
        return userBLL.findUserById(id);
    }

    public boolean enrollStudent(Student student, Course course) {
        return enrollBLL.enrollStudent(student, course);
    }

    public boolean unEnrollStudent(Student student, Course course) {
        return enrollBLL.unEnrollStudent(student, course);
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

    private Exam findExamOfEnrollment(Enrollment enrollment) {
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
        exam.setGrade(grade);
        examBLL.addGrade(exam);
    }

    public void addCourse(String courseName, Teacher teacher) {
        Course course = new Course();
        course.setName(courseName);
        course.setTeacher_id(teacher.getId());
        courseBLL.addCourse(course);
    }
    public Collection<Exam> findExamsForStudentForInterval(Student student, Timestamp from, Timestamp to) {
        Collection<Course> coursesOfStudent = findCoursesOfStudent(student);
        Collection<Exam> exams = coursesOfStudent.stream()
                // find exam for each enrollment
                .map(course -> findExamOfStudentCourse(student, course))
                // discard courses, where students did not receive a grade yet.
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return exams.stream().filter(exam -> 0 <= exam.getDate().compareTo(from)
                && exam.getDate().compareTo(to) <= 0).collect(Collectors.toList());
    }
}
