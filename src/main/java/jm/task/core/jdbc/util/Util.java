package jm.task.core.jdbc.util;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private final String URL = "jdbc:mysql://localhost:3306/practice_1_1_3";
    private final String USERNAME = "root";
    private final String PASSWORD = "rootmysql";

    public Util() {
        try {
            DriverManager.registerDriver(new Driver());
        } catch (SQLException e) {
            throw new RuntimeException("JDBC driver registration error", e);
        }
    }

    public Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            //System.out.println("Connection established successfully");
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException("Cannot connect to database", e);
        }
    }

}
