package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class Util {
    private static final SessionFactory session = buildSession();
    private static final String url = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String username = "MainRoot";
    private static final String password = "root";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection
                    (url, username, password);
        } catch (SQLException e) {
            System.out.println("Connection failed");
        }
        return null;
    }

    public static void close() {
        try {
            if (!Objects.requireNonNull(getConnection()).isClosed()) {
                getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static SessionFactory buildSession() {
        try {
            Configuration configuration = new Configuration();

            configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            configuration.setProperty("hibernate.connection.url", url);
            configuration.setProperty("hibernate.connection.username", username);
            configuration.setProperty("hibernate.connection.password", password);
            configuration.setProperty("hibernate.hbm2ddl.auto", "create-drop");
            configuration.addAnnotatedClass(User.class);

            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            return configuration.buildSessionFactory(serviceRegistry);
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return session;
    }

    public static void closeSessionFactory() {
        session.close();
    }
}
