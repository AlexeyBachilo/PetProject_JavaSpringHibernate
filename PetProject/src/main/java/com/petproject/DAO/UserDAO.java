package com.petproject.DAO;

import com.petproject.entity.Task;
import com.petproject.entity.User;

import java.sql.SQLException;
import java.util.Collection;

public interface UserDAO {
    public void addUser(User user) throws SQLException;
    public void updateUser(Long userId, User user) throws SQLException;
    public User getUserById (Long userId) throws SQLException;
    public Collection getAllUsers() throws SQLException;
    public void deleteUser (User user) throws SQLException;
    public User getUserByTask (Task task) throws SQLException;
}
