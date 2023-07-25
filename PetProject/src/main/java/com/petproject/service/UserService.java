package com.petproject.service;

import com.petproject.DAO.UserDAOImpl;
import com.petproject.entity.Task;
import com.petproject.entity.User;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public class UserService {
    private UserDAOImpl userDAO = new UserDAOImpl();

    public UserService() {
    }

    public void addUser(User user) {
        userDAO.addUser(user);
    }

    public void updateUser(User user) {
        userDAO.updateUser(user.getUserId(), user);
    }

    public void deleteUser(User user) {
        userDAO.deleteUser(user);
    }

    public User getUserById(Long id) {
        return userDAO.getUserById(id);
    }

    @Transactional
    public User getUserByTask(Task task) {
        return userDAO.getUserByTask(task);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public void addTask(Task task) {
        userDAO.addTask(task);
    }

    public void deleteTask(Task task) {
        userDAO.deleteTask(task);
    }

    public void updateTask(Task task) {
        userDAO.updateTask(task);
    }
}