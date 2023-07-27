package com.petproject.application;

import com.petproject.entity.Task;
import com.petproject.entity.User;
import com.petproject.service.TaskService;
import com.petproject.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import static com.petproject.menu.MainMenu.mainMenu;

@SpringBootApplication
@PropertySource("classpath:spring.properties")
public class Main {

    @Autowired
    @Resource(name="userService")
    static UserService userService;
    @Autowired
    @Resource(name="taskService")
    static TaskService taskService;

    public static void main(String[] args) throws IOException {
        SpringApplication.run(Main.class, args);
        List<Task> tasks = taskService.getAllTasks();
        Iterator iterator = tasks.iterator();
        System.out.println("=====All Tasks=====");
        while (iterator.hasNext()){
            Task task = (Task) iterator.next();
            System.out.println("Task Name: " + task.getTaskName() + "\nTask Description: " + task.getTaskDescription()
            +"\nTask Points: " + task.getTaskPoints() + "\nIs Completed: " + ((task.getisCompleted()) ? "Yes" : "No")
            +"\nDeadline: " + task.getDeadline() +"\nAssigned User: " + (userService.getUserByTask(task).getLogin()));
            System.out.println("-------------");
        }

        List<User> users = userService.getAllUsers();
        iterator = users.iterator();
        System.out.println("\n=====All Users=====");
        while(iterator.hasNext()){
            User user = (User) iterator.next();
            System.out.println("User Login: " + user.getLogin() + "\nFirst Name: " + user.getFirstName()
            +"\nLast Name: " + user.getLastName() + "\nCurrent Points: " + user.getUserPoints());
            List<Task> usertasks = taskService.getTasksByUser(user);
            Iterator iterator2 = usertasks.iterator();
            System.out.println("Current Tasks: ");
            while(iterator2.hasNext()) {
                Task task = (Task) iterator2.next();
                System.out.println("      Task Name: " + task.getTaskName() + " | Task Description: " + task.getTaskDescription()
                +" | Is Completed: " + ((task.getisCompleted()) ? "Yes" : "No") + " | Deadline: " + task.getDeadline());
            }
            System.out.println("-------------");
        }
        mainMenu(/*applicationContext*/);
    }

}

//TODO Unit-tests
//TODO Add .properties file to .gitignore
