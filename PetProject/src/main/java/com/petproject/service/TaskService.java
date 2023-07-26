package com.petproject.service;

import com.petproject.DAO.TaskDAOImpl;
import com.petproject.entity.Task;
import com.petproject.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
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

    public void printTask(Task task){
        UserService userService = new UserService();
        System.out.println("Task Name: " + task.getTaskName() + "\nTask Description: " + task.getTaskDescription()
                +"\nTask Points: " + task.getTaskPoints() + "\nIs Completed: " + ((task.getisCompleted()) ? "Yes" : "No")
                +"\nDeadline: " + task.getDeadline() +"\nAssigned User: " + (userService.getUserByTask(task).getLogin()));
    }

    public void printTasksByUser(User user){
        List<Task> tasks = getTasksByUser(user);
        Iterator iterator = tasks.iterator();
        System.out.println("=====User Tasks=====");
        while (iterator.hasNext()){
            Task task = (Task) iterator.next();
            System.out.println("Task Name: " + task.getTaskName() + "\nTask Description: " + task.getTaskDescription()
                    +"\nTask Points: " + task.getTaskPoints() + "\nIs Completed: " + ((task.getisCompleted()) ? "Yes" : "No")
                    +"\nDeadline: " + task.getDeadline());
            System.out.println("-------------");
        }
    }
}
