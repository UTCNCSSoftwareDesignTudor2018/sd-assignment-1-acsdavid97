package data_access;

import data_access.connection.ConnectionFactory;
import data_access.dto.Teacher;
import data_access.dto.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TeacherRepository extends DatabaseRepository<Teacher> implements TeacherRepositoryInterface{
    public TeacherRepository() {
        super(Teacher.class);
    }

    public Teacher findTeacherByUser(User user) throws RepositoryException{
        String findString = "SELECT * FROM `teachers` WHERE " + "user_id = " + "?";
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement findStatement = connection.prepareStatement(findString);
            findStatement.setInt(1, user.getId());
            ResultSet foundElements = findStatement.executeQuery();
            List<Teacher> foundDTOs = createObjects(foundElements);
            findStatement.close();
            connection.close();
            return (foundDTOs.size() > 0) ? foundDTOs.get(0) : null;
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }
}
