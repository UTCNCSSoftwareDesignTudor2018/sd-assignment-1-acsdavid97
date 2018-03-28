package business.facade;

import business.facade.process.*;
import data_access.*;
import data_access.dto.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

public class StudentFacade {
    private UserBLL userBLL = new UserBLL(new UserDatabase());
    private EnrollBLL enrollBLL = new EnrollBLL(new EnrollmentDatabase());
    private CourseBLL courseBLL = new CourseBLL(new CourseDatabase());
    private ExamBLL examBLL = new ExamBLL(new ExamDatabase());
    private StudentBLL studentBLL = new StudentBLL(new StudentDatabase());

    private LoginFacade loginFacade = new LoginFacade();

    public User findUserByStudent(Student student) {
        return this.userBLL.findUserById(student.getUser_id());
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

    public Login findLoginByUser(User user) {
        return loginFacade.findLoginByUser(user);
    }

    public Collection<Course> getCourses() {
        return courseBLL.getCourses();
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

    public Login findLoginByStudent(Student student) {
        User user = findUserByStudent(student);
        if (user == null) {
            return null;
        }
        return findLoginByUser(user);
    }

    public void deleteLogin(Login login) {
        loginFacade.deleteLogin(login);
    }

    public void gradeStudent(Student student, Course course, int grade) {
        Enrollment enrollment = enrollBLL.findEnrollmentByStudentAndCourse(student, course);
        Exam exam = new Exam();
        exam.setDate(new Timestamp(System.currentTimeMillis()));
        exam.setEnrollment_id(enrollment.getId());
        exam.setGrade(grade);
        examBLL.addGrade(exam);
    }

    public boolean updateStudent(Login newLogin, User newUser, Student newStudent) {
        if (!loginFacade.updateLogin(newLogin)){
            return false;
        }

        userBLL.updateUser(newUser);
        studentBLL.updateStudent(newStudent);
        return true;
    }

    public boolean addStudent(Login newLogin, User newUser, Student newStudent) {
        if (!loginFacade.createLoginCredentials(newLogin.getUsername(), newLogin.getPassword())) {
            return false;
        }

        Login createdLogin = loginFacade.findLoginByUsername(newLogin.getUsername());
        newUser.setLogin_id(createdLogin.getId());
        userBLL.addUser(newUser);
        User createdUser = userBLL.findUserByLogin(createdLogin);
        newStudent.setUser_id(createdUser.getId());
        studentBLL.addStudent(newStudent);
        return true;
    }

    public Collection<Student> getStudents() {
        return studentBLL.getStudents();
    }
}
