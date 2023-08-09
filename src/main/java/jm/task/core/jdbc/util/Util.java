package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String USER = "root";
    private static final String PASS = "root";
    private static final String URL = "jdbc:mysql://localhost:3306/k114";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
            connection.setAutoCommit(false);
            System.out.println("Connected.");
        } catch (SQLException e) {
            System.err.println("Connection error.");
            e.printStackTrace();
        }
        return connection;
    }
}
