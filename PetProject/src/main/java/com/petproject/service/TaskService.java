package com.petproject.service;

import com.petproject.entity.Task;
import com.petproject.entity.User;
import com.petproject.repository.TaskRepository;
import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class TaskService {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    @Lazy
    UserService userService;

    protected static Logger logger = LogManager.getLogger("TaskServiceLogger");

    public void addTask(Task task) {
        logger.debug("Adding task");
        taskRepository.saveAndFlush(task);}

    public void updateTask(Task task) {
        logger.debug("Updating task");
        taskRepository.saveAndFlush(task);
    }

    public void deleteTask(Task task) {
        logger.debug("Deleting task");
        taskRepository.delete(task);}

    public Task getTaskById (Long id) {
        logger.debug("Getting task by Id");
        return taskRepository.getReferenceById(id);}

    public List<Task> getTasksByUser (User user) {
        logger.debug("Getting all tasks by User");
        return taskRepository.getTasksByUser(user.getUserId());
    }

    public List<Task> getAllTasks () {
        logger.debug("Getting all tasks");
        return taskRepository.findAll();}

    public void printTask(Task task){
        logger.debug("Printing task");
        System.out.println(task.toString() +"\nAssigned User: " + (userService.getUserByTask(task).getLogin()));
    }

    public void printTasksByUser(User user){
        logger.debug("Printing all tasks by User");
        List<Task> tasks = getTasksByUser(user);
        Iterator<Task> iterator = tasks.iterator();
        System.out.println("=====User Tasks=====");
        while (iterator.hasNext()){
            Task task = iterator.next();
            printTask(task);
            System.out.println("-------------");
        }
    }
}
