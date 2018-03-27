package data_access;

import data_access.connection.ConnectionFactory;
import data_access.dto.Course;
import data_access.dto.Enrollment;
import data_access.dto.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class EnrollmentRepository extends DatabaseRepository<Enrollment> implements EnrollmentRepositoryInterface{
    public EnrollmentRepository() {
        super(Enrollment.class);
    }

    public Collection<Enrollment> findEnrollmentsOfStudent(Student student) throws RepositoryException{
        String findString = "SELECT * FROM `enrollments` WHERE " + "student_id = " + "?";
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement findStatement = connection.prepareStatement(findString);
            findStatement.setInt(1, student.getId());
            ResultSet foundElements = findStatement.executeQuery();
            List<Enrollment> foundDTOs = createObjects(foundElements);
            findStatement.close();
            connection.close();
            return foundDTOs;
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    public Enrollment findEnrollmentByStudentAndCourse(Student student, Course course) throws RepositoryException{
        String findString = "SELECT * FROM `enrollments` WHERE " + "student_id = " + "? AND course_id = " + "?";
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement findStatement = connection.prepareStatement(findString);
            findStatement.setInt(1, student.getId());
            findStatement.setInt(2, course.getId());
            ResultSet foundElements = findStatement.executeQuery();
            List<Enrollment> foundDTOs = createObjects(foundElements);
            findStatement.close();
            connection.close();
            return foundDTOs.size() > 0 ? foundDTOs.get(0) : null;
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }
}
