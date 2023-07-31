package com.petproject.service;

import com.petproject.entity.Task;
import com.petproject.entity.User;

import com.petproject.repository.UserRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    TaskService taskService;

    public void addUser(User user) {
        userRepository.saveAndFlush(user);
    }

    public void updateUser(User user) {
        userRepository.saveAndFlush(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public User getUserById(Long id) {
        return userRepository.getReferenceById(id);
    }

    public User getUserByLogin(String login) {return userRepository.getUserByLogin(login);}

    public User getUserByTask(Task task) {
        return task.getUser();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
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