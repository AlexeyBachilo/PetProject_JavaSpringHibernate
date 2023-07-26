package com.petproject.DAO;

import com.petproject.entity.Task;
import com.petproject.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserDAO {
    void addUser(User user);
    void updateUser(Long userId, User user);
    User getUserById (Long userId);
    User getUserByLogin (String login);
    List<User> getAllUsers();
    void deleteUser (User user);
    User getUserByTask (Task task);
}
