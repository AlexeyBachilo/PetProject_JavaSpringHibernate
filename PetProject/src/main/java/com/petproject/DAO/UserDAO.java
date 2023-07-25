package com.petproject.DAO;

import com.petproject.entity.Task;
import com.petproject.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserDAO {
    void addUser(User user);
    void updateUser(Long userId, User user);
    User getUserById (Long userId);
    List<User> getAllUsers();
    void deleteUser (User user);
    @Transactional
    User getUserByTask (Task task);
    void addTask (Task task);
    void deleteTask (Task task);
    void updateTask (Task task);
}
