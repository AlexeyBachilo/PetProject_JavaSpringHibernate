package com.petproject.service;

import com.petproject.entity.Task;
import com.petproject.entity.User;
import com.petproject.repository.TaskRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
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
    UserService userService;

    public void addTask(Task task) {taskRepository.saveAndFlush(task);}

    public void updateTask(Task task) {
        taskRepository.saveAndFlush(task);
    }

    public void deleteTask(Task task) {taskRepository.delete(task);}

    public Task getTaskById (Long id) {return taskRepository.getReferenceById(id);}

    public List<Task> getTasksByUser (User user) {
        return taskRepository.getTasksByUser(user.getUserId());
    }

    public List<Task> getAllTasks () {return taskRepository.findAll();}

    public void printTask(Task task){
        System.out.println(task.toString() +"\nAssigned User: " + (userService.getUserByTask(task).getLogin()));
    }

    public void printTasksByUser(User user){
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
