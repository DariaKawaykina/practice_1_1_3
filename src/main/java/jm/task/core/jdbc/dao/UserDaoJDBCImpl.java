package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Util util;

    private static final String CREATE = "CREATE TABLE IF NOT EXISTS user (id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(100), last_name VARCHAR(100), age TINYINT UNSIGNED)";
    private static final String DROP = "DROP TABLE IF EXISTS user";
    private static final String SAVE = "INSERT INTO user (name, last_name, age) VALUES(?,?,?)";
    private static final String REMOVE_BY_ID = "DELETE FROM user WHERE id=?;";
    private static final String GET_ALL = "SELECT * FROM user";
    private static final String CLEAN_TABLE = "TRUNCATE TABLE user";


    public UserDaoJDBCImpl() {
    }

    public UserDaoJDBCImpl(Util util) {
        this.util = util;
    }

    public void createUsersTable() {
        try (Connection connection = util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE)) {
            connection.setAutoCommit(false);
            try {
                preparedStatement.execute();

                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DROP)) {
            connection.setAutoCommit(false);
            try {
                preparedStatement.execute();

                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE)) {
            connection.setAutoCommit(false);
            try {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, lastName);
                preparedStatement.setByte(3, age);
                preparedStatement.executeUpdate();

                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_BY_ID)) {
            connection.setAutoCommit(false);
            try {
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();

                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> listOfUsers = new ArrayList<>();
        try (Connection connection = util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL)) {
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()) {
                User user = new User();
                user.setId(res.getLong("id"));
                user.setName(res.getString("name"));
                user.setLastName(res.getString("last_name"));
                user.setAge(res.getByte("age"));
                listOfUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfUsers;
    }

    public void cleanUsersTable() {
        try (Connection connection = util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CLEAN_TABLE)) {
            connection.setAutoCommit(false);
            try {
                preparedStatement.execute();

                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
