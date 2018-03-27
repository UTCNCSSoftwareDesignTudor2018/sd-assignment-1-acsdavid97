package business;

import data_access.EnrollmentDatabase;
import data_access.EnrollmentRepository;
import data_access.dto.Course;
import data_access.dto.Enrollment;
import data_access.dto.Student;

import java.util.Collection;

public class EnrollBLL {
    private EnrollmentRepository enrollmentRepository;

    EnrollBLL(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public boolean enrollStudent(Student student, Course course) {
        if (enrollmentRepository.findEnrollmentByStudentAndCourse(student, course) != null) {
            // there is already such an enrollment.
            return false;
        }
        Enrollment enrollment = new Enrollment();
        enrollment.setCourse_id(course.getId());
        enrollment.setStudent_id(student.getId());
        this.enrollmentRepository.add(enrollment);
        return true;
    }

    public Enrollment findEnrollmentByStudentAndCourse(Student student, Course course) {
        return enrollmentRepository.findEnrollmentByStudentAndCourse(student, course);
    }

    public boolean unenrollStudent(Student student, Course course) {
        Enrollment enrollment = enrollmentRepository.findEnrollmentByStudentAndCourse(student, course);
        if (enrollment == null) {
            return false;
        }
        return enrollmentRepository.delete(enrollment);
    }

    public Collection<Enrollment> findEnrollmentsOfStudent(Student student) {
        return enrollmentRepository.findEnrollmentsOfStudent(student);
    }
}
