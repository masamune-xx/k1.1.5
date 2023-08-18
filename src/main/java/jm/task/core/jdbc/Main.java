package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Владимир", "Ленин", (byte) 70);
        userService.saveUser("Иосиф", "Сталин", (byte) 65);
        userService.saveUser("Никита", "Хрущёв", (byte) 62);
        userService.saveUser("Леонид", "Брежнев", (byte) 48);
        for (User user : userService.getAllUsers()) {
            System.out.println(user);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
