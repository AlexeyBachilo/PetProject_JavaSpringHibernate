package com.petproject.service;

import com.petproject.DAO.TaskDAOImpl;
import com.petproject.entity.Task;
import com.petproject.entity.User;

import java.sql.SQLException;
import java.util.List;

public class TaskService {
    private TaskDAOImpl taskDAO = new TaskDAOImpl();

    public void addTask(Task task) throws SQLException {taskDAO.addTask(task);}

    public void updateTask(Task task) throws SQLException {taskDAO.updateTask(task.getTaskId(),task);}

    public void deleteTask(Task task) throws SQLException {taskDAO.deleteTask(task);}

    public Task getTaskById (Long id) throws SQLException {return taskDAO.getTaskById(id);}

    public List<Task> getTasksByUser (User user) throws SQLException {return taskDAO.getTasksByUser(user);}

    public List<Task> getAllTasks () throws SQLException {return taskDAO.getAllTasks();}

}
