package com.petproject;

import com.petproject.entity.Task;
import com.petproject.entity.User;
import com.petproject.service.TaskService;
import com.petproject.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.List;

import static com.petproject.menu.MainMenu.mainMenu;

@SpringBootApplication(scanBasePackages = {"com.*"})
@ComponentScan(basePackages = {"com.*"})
@EntityScan("com.*")
@PropertySource("classpath:application.properties")
public class Main implements CommandLineRunner {

    @Autowired
    @Resource(name="userService")
    UserService userService;
    @Autowired
    @Resource(name="taskService")
    TaskService taskService;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String ...args) throws Exception{
        ApplicationContext context = new ClassPathXmlApplicationContext("beanLocations.xml");
        /*taskService = (TaskService) context.getBean("taskService");
        userService = (UserService) context.getBean("userService");*/
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
        mainMenu();
    }
}

//TODO Unit-tests
