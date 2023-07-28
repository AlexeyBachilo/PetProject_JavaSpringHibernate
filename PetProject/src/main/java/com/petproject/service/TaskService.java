package com.petproject.service;

import com.petproject.DAO.TaskDAOImpl;
import com.petproject.entity.Task;
import com.petproject.entity.User;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceUnit;
import java.util.Iterator;
import java.util.List;

@Service("taskService")
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class TaskService {
    @Autowired
    @Resource(name = "taskDAO")
    private TaskDAOImpl taskDAO;
    @Autowired
    @Resource(name = "userService")
    private UserService userService;

    public TaskService() {
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
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
