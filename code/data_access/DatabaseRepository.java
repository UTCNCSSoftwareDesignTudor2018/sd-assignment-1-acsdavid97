package data_access;

import data_access.GenericRepository;
import data_access.RepositoryException;
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


    private String createSelectQuery(String fieldName, String value) {
        return "SELECT * FROM `"
                + databaseTableName
                + "` WHERE " + fieldName + "=" + value;
    }

    private String createDeleteQuery(String fieldName, String value) {
        return "DELETE FROM `"
                + databaseTableName
                + "` WHERE " + fieldName + "=" + value;
    }

    private String createInsertQuery(T toInsert) throws RepositoryException {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO `");
        sb.append(databaseTableName);
        sb.append("` (");

        for(Field field : type.getDeclaredFields()) {
            field.setAccessible(true);
            if (!field.getName().equals("id")) {
                sb.append("`").append(field.getName()).append("`,");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(") VALUES (");

        for(Field field : type.getDeclaredFields()) {
            field.setAccessible(true);
            if (!field.getName().equals("id")) {
                try {
                    sb.append("'").append(field.get(toInsert)).append("',");
                } catch (IllegalAccessException e) {
                    throw new RepositoryException(e);
                }
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(");");
        return sb.toString();
    }

    private String createUpdateQuery(String idField, String value, T toUpdate) throws RepositoryException {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE `");
        sb.append(type.getSimpleName());
        sb.append("` SET ");
        for(Field field : type.getDeclaredFields()) {
            field.setAccessible(true);
            sb.append("`").append(field.getName()).append("`=");
            try {
                sb.append("'").append(field.get(toUpdate)).append("',");
            } catch (IllegalAccessException e) {
                throw new RepositoryException(e);
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(" WHERE `").append(idField).append("`='").append(value).append("';");

        return sb.toString();
    }

    private List<T> executeQuery(String query) throws RepositoryException {
        List<T> results = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            results = createObjects(resultSet);
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }

        return results;
    }

    private List<T> getObjectsFromDB(String field, String value) throws RepositoryException {
        String query = createSelectQuery(field, value);
        return this.executeQuery(query);
    }

    private List<T> createObjects(ResultSet resultSet) {
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

    private boolean executeUpdate(String query) throws RepositoryException {
        Connection connection = null;
        PreparedStatement statement = null;
        int retValue = 0;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            retValue = statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }

        return (retValue != 0);
    }

    public T findById(int id) throws RepositoryException {
        List<T> results = getObjectsFromDB("id", Integer.toString(id));
        return results.size() > 0 ? results.get(0) : null;
    }

    public void add(T toAdd) throws RepositoryException {
        String insertQuery = createInsertQuery(toAdd);
        executeUpdate(insertQuery);
    }

    public Collection<T> getAll() throws RepositoryException {
        return getObjectsFromDB("1", "1");
    }

    public boolean update(T toUpdate) throws RepositoryException {
        String updateQuery = createUpdateQuery("id", Integer.toString(toUpdate.getId()), toUpdate);
        return executeUpdate(updateQuery);
    }

    public boolean delete(T toDelete) throws RepositoryException {
        String query = createDeleteQuery("id", Integer.toString(toDelete.getId()));
        return executeUpdate(query);
    }
}
