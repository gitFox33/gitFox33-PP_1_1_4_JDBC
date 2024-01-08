package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args)  {

        UserDao userDaoJDBC = new UserDaoJDBCImpl();
        UserService userServiceJDBC = new UserServiceImpl(userDaoJDBC);
        method(userServiceJDBC);

        UserDao userDaoHiber = new UserDaoHibernateImpl();
        UserService userServiceHiber = new UserServiceImpl(userDaoHiber);
        method(userServiceHiber);

    }
    public static void method (UserService userService)  {

            userService.createUsersTable();

            userService.saveUser("Ivan", "Ivanov", (byte) 20);
            userService.saveUser("Petr", "Petrov", (byte) 25);
            userService.saveUser("Sidor", "Sidorov", (byte) 30);
            userService.saveUser("Alexey", "Alexeev", (byte) 35);

        for (User user : userService.getAllUsers()) {
            System.out.println(user);
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
