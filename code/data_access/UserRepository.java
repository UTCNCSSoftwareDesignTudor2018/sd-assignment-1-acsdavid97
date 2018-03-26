package data_access;

import data_access.connection.ConnectionFactory;
import data_access.dto.Login;
import data_access.dto.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserRepository extends DatabaseRepository<User>{
    public UserRepository() {
        super(User.class);
    }

    public User findUserByLogin(Login login) {
        String findString = "SELECT * FROM `users` WHERE " + "login_id = " + "?";
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement findStatement = connection.prepareStatement(findString);
            findStatement.setInt(1, login.getId());
            ResultSet foundElements = findStatement.executeQuery();
            List<User> foundDTOs = createObjects(foundElements);
            findStatement.close();
            connection.close();
            return (foundDTOs.size() > 0) ? foundDTOs.get(0) : null;
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }
}
