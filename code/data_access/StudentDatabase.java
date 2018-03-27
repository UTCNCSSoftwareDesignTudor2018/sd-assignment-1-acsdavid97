package data_access;

import data_access.connection.ConnectionFactory;
import data_access.dto.Student;
import data_access.dto.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StudentDatabase extends DatabaseRepository<Student> implements StudentRepository {
    public StudentDatabase() {
        super(Student.class);
    }

    public Student findStudentByUser(User user) throws RepositoryException{
        String findString = "SELECT * FROM `students` WHERE " + "user_id = " + "?";
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement findStatement = connection.prepareStatement(findString);
            findStatement.setInt(1, user.getId());
            ResultSet foundElements = findStatement.executeQuery();
            List<Student> foundDTOs = createObjects(foundElements);
            findStatement.close();
            connection.close();
            return (foundDTOs.size() > 0) ? foundDTOs.get(0) : null;
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }
}
