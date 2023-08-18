package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String sql = "create table if not exists users (id bigint not null auto_increment, name varchar(64), last_name varchar(64), age int, primary key(id))";
        Transaction tx = null;
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            tx = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "drop table if exists users";
        Transaction tx = null;
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            tx = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction tx = null;
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            tx = session.beginTransaction();
            session.save(new User(name, lastName, age));
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction tx = null;
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            tx = session.beginTransaction();
            session.delete(session.get(User.class, id));
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            users = session.createQuery("from User").getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        String sql = "truncate table users";
        Transaction tx = null;
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            tx = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
}
