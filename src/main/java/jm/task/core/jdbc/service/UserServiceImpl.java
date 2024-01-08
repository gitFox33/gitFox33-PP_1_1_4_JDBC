package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao dao;

    public UserServiceImpl(UserDao dao) {
        this.dao = dao;
    }

    public UserServiceImpl() {

    }


    @Override
    public void createUsersTable() {
        try {
            dao.createUsersTable();
        } catch (Exception e) {
            System.out.println("Error create table " + e.getMessage());
        }

    }

    @Override
    public void dropUsersTable() {
        try {
            dao.dropUsersTable();
        } catch (Exception e) {
            System.out.println("Error drop table " + e.getMessage());
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            dao.saveUser(name, lastName, age);
        } catch (Exception e) {
            System.out.println("Error save user " + e.getMessage());
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            dao.removeUserById(id);
        } catch (Exception e) {
            System.out.println("Error remove user " + e.getMessage());
        }
    }


    @Override
    public List<User> getAllUsers() {
        try {
            return dao.getAllUsers();
        } catch (Exception e) {
            System.out.println("Error get all users " + e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public void cleanUsersTable() {
        try {
            dao.cleanUsersTable();
        } catch (Exception e) {
            System.out.println("Error clean table " + e.getMessage());
        }
    }
}

