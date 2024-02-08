package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static Connection connection;
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS my_table (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(50),
                    lastName VARCHAR(50),
                    age INT
                )
                """;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void dropUsersTable() {
        String sql = """
                DROP TABLE IF EXISTS my_table;
                """;

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = """
                INSERT INTO my_table (name, lastName, age) VALUES (?, ?, ?)
                """;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String sql = """
                DELETE FROM my_table WHERE id = ?
                """;
        
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();

        String sql = """
                SELECT * FROM my_table;
                """;
        
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)){
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setAge(resultSet.getByte("age"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));

                list.add(user);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cleanUsersTable() {
        String sql = """
                TRUNCATE TABLE my_table;
                """;
        
        try (Statement statement = connection.createStatement()){
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
