package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDAO;

    public UserServiceImpl() {
        this.userDAO = new UserDaoJDBCImpl(new Util());
    }

    public void createUsersTable() {
        userDAO.createUsersTable();
    }

    public void dropUsersTable() {
        userDAO.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userDAO.saveUser(name, lastName, age);
        System.out.println("User с именем - " + name + " добавлен в базу данных");
    }

    public void removeUserById(long id) {
        userDAO.removeUserById(id);
    }

    public List<User> getAllUsers() {
        List<User> listOfUsers = userDAO.getAllUsers();
        for (User user : listOfUsers) {
            System.out.println(user);
        }
        return listOfUsers;
    }

    public void cleanUsersTable() {
        userDAO.cleanUsersTable();
    }
}
