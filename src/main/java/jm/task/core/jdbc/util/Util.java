package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String USER = "root";
    private static final String PASS = "root";
    private static final String URL = "jdbc:mysql://localhost:3306/k114";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
            connection.setAutoCommit(false);
            System.out.println("Подключено к БД");
        } catch (SQLException e) {
            System.err.println("Ошибка подключения к БД");
            e.printStackTrace();
        }
        return connection;
    }

    public static SessionFactory getSessionFactory() {
        SessionFactory factory = null;
        try {
            Configuration config = new Configuration();
            Properties properties = new Properties();
            properties.put(Environment.URL, URL);
            properties.put(Environment.USER, USER);
            properties.put(Environment.PASS, PASS);
            properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
            properties.put(Environment.SHOW_SQL, "true");
            properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            config.setProperties(properties);
            config.addAnnotatedClass(User.class);
            ServiceRegistry registry =
                    new StandardServiceRegistryBuilder()
                            .applySettings(config.getProperties()).build();
            factory = config.buildSessionFactory(registry);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return factory;
    }
}
