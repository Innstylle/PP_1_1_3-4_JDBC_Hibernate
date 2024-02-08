package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class Util {
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
}
