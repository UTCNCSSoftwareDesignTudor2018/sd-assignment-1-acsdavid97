package business;

import data_access.EnrollmentRepository;
import data_access.dto.Course;
import data_access.dto.Enrollment;
import data_access.dto.Student;

import java.util.Collection;

public class EnrollBLL {
    private EnrollmentRepository enrollmentRepository;

    public EnrollBLL() {
        this.enrollmentRepository = new EnrollmentRepository();
    }

    public boolean enrollStudent(Student student, Course course) {
        // todo check if student is alerady enrolled
        Enrollment enrollment = new Enrollment();
        enrollment.setCourse_id(course.getId());
        enrollment.setStudent_id(student.getId());
        this.enrollmentRepository.add(enrollment);
        return true;
    }

    public Collection<Enrollment> findEnrollmentsOfStudent(Student student) {
        return enrollmentRepository.findEnrollmentsOfStudent(student);
    }
}
