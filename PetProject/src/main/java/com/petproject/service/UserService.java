package com.petproject.service;

import com.petproject.DAO.UserDAOImpl;
import com.petproject.entity.Task;
import com.petproject.entity.User;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service("userService")
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class UserService {
    private UserDAOImpl userDAO;

    public UserService() {
    }

    public UserDAOImpl getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
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

    public User getUserByTask(Task task) {
        return userDAO.getUserByTask(task);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public void completeTask(Task task){
        TaskService taskService = new TaskService();
        task.setisCompleted(!task.getisCompleted());
        User user = getUserByTask(task);
        user.setUserPoints(task.getisCompleted() ? user.getUserPoints() + task.getTaskPoints() : user.getUserPoints() - task.getTaskPoints());
        taskService.updateTask(task);
        updateUser(user);
    }
}