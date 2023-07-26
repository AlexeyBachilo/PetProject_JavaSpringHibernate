package com.petproject.service;

import com.petproject.DAO.TaskDAOImpl;
import com.petproject.entity.Task;
import com.petproject.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("taskService")
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class TaskService {
    private TaskDAOImpl taskDAO;

    public TaskService() {
    }

    public TaskDAOImpl getTaskDAO() {
        return taskDAO;
    }

    public void setTaskDAO(TaskDAOImpl taskDAO) {
        this.taskDAO = taskDAO;
    }

    public void addTask(Task task) {taskDAO.addTask(task);}

    public void updateTask(Task task) {taskDAO.updateTask(task.getTaskId(),task);}

    public void deleteTask(Task task) {taskDAO.deleteTask(task);}

    public Task getTaskById (Long id) {return taskDAO.getTaskById(id);}

    public List<Task> getTasksByUser (User user) {return taskDAO.getTasksByUser(user);}

    public List<Task> getAllTasks () {return taskDAO.getAllTasks();}

}
