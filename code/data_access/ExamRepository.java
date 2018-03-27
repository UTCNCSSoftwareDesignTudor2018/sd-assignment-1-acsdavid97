package data_access;

import data_access.connection.ConnectionFactory;
import data_access.dto.Enrollment;
import data_access.dto.Exam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ExamRepository extends DatabaseRepository<Exam> implements ExamRepositoryInterface{
    public ExamRepository() {
        super(Exam.class);
    }

    public Exam findExamFromEnrollment(Enrollment enrollment) throws RepositoryException{
        String findString = "SELECT * FROM `exams` WHERE " + "enrollment_id = " + "?";
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement findStatement = connection.prepareStatement(findString);
            findStatement.setInt(1, enrollment.getId());
            ResultSet foundElements = findStatement.executeQuery();
            List<Exam> foundDTOs = createObjects(foundElements);
            findStatement.close();
            connection.close();
            return foundDTOs.size() > 0 ? foundDTOs.get(0) : null;
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }
}
