package data_access;

import data_access.connection.ConnectionFactory;
import data_access.dto.Login;
import data_access.dto.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LoginRepository extends DatabaseRepository<Login>{
    public LoginRepository() {
        super(Login.class);
    }

    public Login findByUsername(String username) throws RepositoryException{
        String findString = "SELECT * FROM `logins` WHERE `username` = ?";
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement findStatement = connection.prepareStatement(findString);
            findStatement.setString(1, username);
            ResultSet foundElements = findStatement.executeQuery();
            List<Login> foundDTOs = createObjects(foundElements);
            findStatement.close();
            connection.close();
            return (foundDTOs.size() > 0) ? foundDTOs.get(0) : null;
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }
}
