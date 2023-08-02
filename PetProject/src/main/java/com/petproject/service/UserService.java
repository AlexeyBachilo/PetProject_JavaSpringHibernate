package com.petproject.service;

import com.petproject.entity.Task;
import com.petproject.entity.User;

import com.petproject.repository.UserRepository;
import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    @Lazy
    TaskService taskService;

    protected static Logger logger = LogManager.getLogger("UserServiceLogger");

    public void addUser(User user) {
        logger.debug("Adding user");
        userRepository.saveAndFlush(user);
    }

    public void updateUser(User user) {
        logger.debug("Updating user");
        userRepository.saveAndFlush(user);
    }

    public void deleteUser(User user) {
        logger.debug("Deleting user");
        userRepository.delete(user);
    }

    public User getUserById(Long id) {
        logger.debug("Getting user by Id");
        return userRepository.getReferenceById(id);
    }

    public User getUserByLogin(String login) {
        logger.debug("Getting user by Login");
        return userRepository.getUserByLogin(login);}

    public User getUserByTask(Task task) {
        logger.debug("Getting user by Task");
        return task.getUser();
    }

    public List<User> getAllUsers() {
        logger.debug("Getting all users");
        return userRepository.findAll();
    }

    public void completeTask(Task task){
        logger.debug("Marking task as completed");
        task.setisCompleted(!task.getisCompleted());
        User user = getUserByTask(task);
        logger.debug("Adding taskpoint to user");
        user.setUserPoints(task.getisCompleted() ? user.getUserPoints() + task.getTaskPoints() : user.getUserPoints() - task.getTaskPoints());
        taskService.updateTask(task);
        updateUser(user);
    }

    public void printUser(User user, boolean printTasks){
        logger.debug("Printing user");
        System.out.println(user.toString());
        if(printTasks){
            taskService.printTasksByUser(user);
            }
        }

}