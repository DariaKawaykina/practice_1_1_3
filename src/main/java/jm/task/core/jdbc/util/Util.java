package jm.task.core.jdbc.util;

import com.mysql.cj.jdbc.Driver;
import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private final String URL = "jdbc:mysql://localhost:3306/practice_1_1_3";
    private final String USERNAME = "root";
    private final String PASSWORD = "rootmysql";
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String DIALECT = "org.hibernate.dialect.MySQLDialect";

    private SessionFactory sessionFactory;

    public Util() {
        try {
            // JDBC Driver
            DriverManager.registerDriver(new Driver());

            // Hibernate
            Properties properties = new Properties();
            Configuration configuration = new Configuration();

            properties.put("hibernate.driver_class", DRIVER);
            properties.put("hibernate.connection.url", URL);
            properties.put("hibernate.connection.username", USERNAME);
            properties.put("hibernate.connection.password", PASSWORD);
            properties.put("hibernate.dialect", DIALECT);
            properties.put("hibernate.show_sql", "true");
            properties.put("hibernate.current_session_context_class", "thread");

            configuration.setProperties(properties);

            configuration.addAnnotatedClass(User.class);

            sessionFactory = configuration.buildSessionFactory();

        } catch (Exception e) {
            throw new RuntimeException("Driver registration error", e);
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

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
