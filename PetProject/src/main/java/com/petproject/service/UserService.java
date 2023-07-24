package com.petproject.service;

import com.petproject.DAO.UserDAOImpl;
import com.petproject.entity.Task;
import com.petproject.entity.User;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.sql.SQLException;
import java.util.List;

public class UserService{
    private UserDAOImpl userDAO = new UserDAOImpl();

    public UserService(){}

    public void addUser(User user) throws SQLException {userDAO.addUser(user);}

    public void updateUser(User user) throws SQLException {userDAO.updateUser(user.getUserId(), user);}

    public void deleteUser(User user) throws SQLException {userDAO.deleteUser(user);}

    public User getUserById (Long id) throws SQLException {return userDAO.getUserById(id);}

    @Transactional
    public User getUserByTask (Task task) throws SQLException {return userDAO.getUserByTask(task);}

    public List<User> getAllUsers () throws SQLException {return userDAO.getAllUsers();}

}
