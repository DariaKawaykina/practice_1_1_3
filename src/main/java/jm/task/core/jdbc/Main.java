package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Harry", "Potter", (byte) 28);
        userService.saveUser("Hermione", "Granger", (byte) 29);
        userService.saveUser("Ginny", "Weasley", (byte) 27);
        userService.saveUser("Cedric", "Diggory", (byte) 18);

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();

    }
}
