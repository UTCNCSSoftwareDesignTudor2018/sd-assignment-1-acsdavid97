package data_access;

import data_access.dto.Course;
import data_access.dto.Enrollment;
import data_access.dto.Student;

import java.util.Collection;

public interface EnrollmentRepository extends GenericRepository<Enrollment>{

    Collection<Enrollment> findEnrollmentsOfStudent(Student student) throws RepositoryException;
    Enrollment findEnrollmentByStudentAndCourse(Student student, Course course) throws RepositoryException;
}
