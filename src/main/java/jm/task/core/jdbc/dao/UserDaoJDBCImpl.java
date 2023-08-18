package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
//    private static final Connection CONNECTION = Util.getConnection();
    private static final Connection CONNECTION = null;

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "create table if not exists users (id bigint not null auto_increment, name varchar(64), last_name varchar(64), age int, primary key(id))";
        try (Statement statement = CONNECTION.createStatement()) {
            statement.executeUpdate(sql);
            CONNECTION.commit();
        } catch (SQLException e) {
            try {
                CONNECTION.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String sql = "drop table if exists users";
        try (Statement statement = CONNECTION.createStatement()) {
            statement.executeUpdate(sql);
            CONNECTION.commit();
        } catch (SQLException e) {
            try {
                CONNECTION.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, last_name, age) VALUES (?, ?, ?)";
        try (PreparedStatement ps = CONNECTION.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();
            CONNECTION.commit();

        } catch (SQLException e) {
            try {
                CONNECTION.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sql = "delete from users where id = ?";
        try (PreparedStatement ps = CONNECTION.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
            CONNECTION.commit();
        } catch (SQLException e) {
            try {
                CONNECTION.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        String sql = "select * from users";
        List<User> users = new ArrayList<>();
        try (ResultSet rs = CONNECTION
                .createStatement()
                .executeQuery(sql)) {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("last_name"));
                user.setAge(rs.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        String sql = "truncate table users";
        try (Statement statement = CONNECTION.createStatement()) {
            statement.executeUpdate(sql);
            CONNECTION.commit();
        } catch (SQLException e) {
            try {
                CONNECTION.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
