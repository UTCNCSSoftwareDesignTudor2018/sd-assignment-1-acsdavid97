package data_access;

import data_access.connection.ConnectionFactory;
import data_access.dto.GenericDTO;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DatabaseRepository<T extends GenericDTO> implements GenericRepository<T> {
    private final Class<? extends T> type;
    private final String databaseTableName;

    protected DatabaseRepository(Class<? extends T> subClass){
        this.type = subClass;
        this.databaseTableName = type.getSimpleName().toLowerCase() + "s";
    }

    List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<>();

        try {
            while (resultSet.next()) {
                T instance = type.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    field.setAccessible(true);
                    Object value = resultSet.getObject(field.getName());
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (SQLException | IllegalAccessException | IntrospectionException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return list;
    }

    private String generatePlaceholders(int placeholderCount) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < placeholderCount - 1; i++) {
            stringBuilder.append("?, ");
        }
        stringBuilder.append("?");
        return stringBuilder.toString();
    }

    public T findById(int id) throws RepositoryException {
        String findString = "SELECT * FROM `" + databaseTableName
                + "` WHERE " + "id = " + "?";
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement findStatement = connection.prepareStatement(findString);
            findStatement.setInt(1, id);
            ResultSet foundElements = findStatement.executeQuery();
            List<T> foundDTOs = createObjects(foundElements);
            findStatement.close();
            connection.close();
            return (foundDTOs.size() > 0) ? foundDTOs.get(0) : null;
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    private List<Field> getFieldsWithoutID() {
        Field[] fieldsArray = type.getDeclaredFields();
        List<Field> fieldList = new ArrayList<>();
        for(Field field : fieldsArray) {
            field.setAccessible(true);
            if (!field.getName().equals("id")) {
                fieldList.add(field);
            }
        }
        return fieldList;
    }

    public void add(T toAdd) throws RepositoryException {
        List<Field> fields = getFieldsWithoutID();
        String insertHeadString = "INSERT INTO  `" + databaseTableName + "`";

        StringBuilder stringBuilder = new StringBuilder(insertHeadString);
        stringBuilder.append("(");
        for (Field field : getFieldsWithoutID()) {
            stringBuilder.append("`").append(field.getName()).append("`,");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append(")");
        String insertString = stringBuilder.toString()
                 + " VALUES (" + generatePlaceholders(fields.size()) + ")";

        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement insertStatement = connection.prepareStatement(insertString);
            int fieldIndex = 1;
            for(Field field : fields) {
                insertStatement.setObject(fieldIndex, field.get(toAdd));
                fieldIndex++;
            }
            insertStatement.executeUpdate();
            insertStatement.close();
            connection.close();
        } catch (SQLException | IllegalAccessException e) {
            throw new RepositoryException(e);
        }
    }

    public Collection<T> getAll() throws RepositoryException {
        String selectAllString = "SELECT * FROM `" + databaseTableName + "`";
        try {
            Connection connection = ConnectionFactory.getConnection();
            Statement selectAllStatement = connection.createStatement();
            ResultSet results = selectAllStatement.executeQuery(selectAllString);
            Collection<T> resultObjects = createObjects(results);
            selectAllStatement.close();
            connection.close();
            return resultObjects;
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    public boolean update(T toUpdate) throws RepositoryException {
        String updateHeadString = "UPDATE `" + databaseTableName + "`"
                + " SET ";
        StringBuilder stringBuilder = new StringBuilder(updateHeadString);
        for (Field field : getFieldsWithoutID()) {
            stringBuilder.append("`").append(field.getName()).append("` = ?,");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append(" WHERE ").append("`id` = ?");

        String updateString = stringBuilder.toString();

        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement updateStatement = connection.prepareStatement(updateString);
            int fieldIndex = 1;
            for(Field field : getFieldsWithoutID()) {
                updateStatement.setObject(fieldIndex, field.get(toUpdate));
                fieldIndex++;
            }
            updateStatement.setInt(fieldIndex, toUpdate.getId());

            int updatedRows = updateStatement.executeUpdate();
            updateStatement.close();
            connection.close();
            return (updatedRows != 0);
        } catch (SQLException | IllegalAccessException e) {
            throw new RepositoryException(e);
        }
    }

    public boolean delete(T toDelete) throws RepositoryException {
        return delete(toDelete.getId());
    }

    @Override
    public boolean delete(int id) throws RepositoryException {
        String deleteStatment = "DELETE FROM `" + databaseTableName
                + "` WHERE " + "id = ?";
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement deleteStatement = connection.prepareStatement(deleteStatment);
            deleteStatement.setInt(1, id);
            int rowsDeleted = deleteStatement.executeUpdate();
            deleteStatement.close();
            connection.close();
            return (rowsDeleted != 0);
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }
}
