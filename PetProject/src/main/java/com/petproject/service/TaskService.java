package com.petproject.service;

import com.petproject.DAO.TaskDAOImpl;
import com.petproject.entity.Task;
import com.petproject.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class TaskService {
    private TaskDAOImpl taskDAO = new TaskDAOImpl();

    public void addTask(Task task) {taskDAO.addTask(task);}

    public void updateTask(Task task) {taskDAO.updateTask(task.getTaskId(),task);}

    public void deleteTask(Task task) {taskDAO.deleteTask(task);}

    public Task getTaskById (Long id) {return taskDAO.getTaskById(id);}

    @Transactional
    public List<Task> getTasksByUser (User user) {return taskDAO.getTasksByUser(user);}

    public List<Task> getAllTasks () {return taskDAO.getAllTasks();}

}
