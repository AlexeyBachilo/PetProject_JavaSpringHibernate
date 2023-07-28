package com.petproject.service;

import com.petproject.DAO.UserDAOImpl;
import com.petproject.entity.Task;
import com.petproject.entity.User;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceUnit;
import java.util.Iterator;
import java.util.List;

@Service("userService")
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class UserService {
    @Autowired
    @Resource(name = "userDAO")
    private UserDAOImpl userDAO;
    @Autowired
    @Resource(name = "taskService")
    private TaskService taskService;

    public UserService() {
    }

    public TaskService getTaskService() {
        return taskService;
    }

    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
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

    public User getUserByLogin(String login) {return userDAO.getUserByLogin(login);}

    public User getUserByTask(Task task) {
        return userDAO.getUserByTask(task);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public void completeTask(Task task){
        task.setisCompleted(!task.getisCompleted());
        User user = getUserByTask(task);
        user.setUserPoints(task.getisCompleted() ? user.getUserPoints() + task.getTaskPoints() : user.getUserPoints() - task.getTaskPoints());
        taskService.updateTask(task);
        updateUser(user);
    }

    public void printUser(User user, boolean printTasks){
        System.out.println(user.toString());
        if(printTasks){
            taskService.printTasksByUser(user);
            }
        }

}