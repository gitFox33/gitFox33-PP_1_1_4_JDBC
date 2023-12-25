package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    final private Connection connection = Util.getConnection();

    @Override
    public void createUsersTable() {

        try {
            try (Statement statement = connection.createStatement()) {

                statement.execute("CREATE TABLE IF NOT EXISTS USERS " +
                        "(id BIGINT PRIMARY KEY" +
                        " AUTO_INCREMENT, name VARCHAR(255), " +
                        "lastName VARCHAR(255), age TINYINT)");

            }
        } catch (SQLException e) {
            System.out.println("Error create table " + e.getMessage());
        }
        System.out.println("Success create table");
    }
    @Override
    public void dropUsersTable()  {

        try {
            try (Statement statement = connection.createStatement()) {
                statement.execute("DROP TABLE IF EXISTS USERS");
            }
        } catch (SQLException e) {
            System.out.println("Error drop table " + e.getMessage());
        }
        System.out.println("Success drop table");
    }
    @Override
    public void saveUser(String name, String lastName, byte age)  {

        String sql = "INSERT INTO USERS (name, lastName, age) VALUES (?, ?, ?)";

        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setString(1, name);
                preparedStatement.setString(2, lastName);
                preparedStatement.setByte(3, age);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error save user " + e.getMessage());
        }
        System.out.println("Success save user");
    }
    @Override
    public void removeUserById(long id)  {

        try {
            try (Statement statement = connection.createStatement()) {

                statement.execute("DELETE FROM USERS WHERE id = " + id);

            }
        } catch (SQLException e) {
            System.out.println("Error remove user " + e.getMessage());
        }
        System.out.println("Success remove user");
    }
    @Override
    public List<User> getAllUsers()  {

        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM USERS";

        try {
            try (Statement statement = connection.createStatement()) {

                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getLong("id"));
                    user.setName(resultSet.getString("name"));
                    user.setLastName(resultSet.getString("lastName"));
                    user.setAge(resultSet.getByte("age"));
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error get all users " + e.getMessage());
        }
        System.out.println("Success get all users");
        return users;
    }
    @Override
    public void cleanUsersTable() {

        try {
            try (Statement statement = connection.createStatement()) {

                statement.execute("TRUNCATE TABLE USERS");

            }
        } catch (SQLException e) {
            System.out.println("Error clean table " + e.getMessage());
        }
        System.out.println("Success clean table");
    }
}
