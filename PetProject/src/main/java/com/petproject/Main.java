package com.petproject;

import com.petproject.entity.Task;
import com.petproject.entity.User;
import com.petproject.service.TaskService;
import com.petproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Iterator;
import java.util.List;

import static com.petproject.menu.MainMenu.mainMenu;

@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired
    UserService userService;
    @Autowired
    TaskService taskService;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String ...args) throws Exception{
        List<Task> tasks = taskService.getAllTasks();
        Iterator iterator = tasks.iterator();
        System.out.println("=====All Tasks=====");
        while (iterator.hasNext()){
            Task task = (Task) iterator.next();
            taskService.printTask(task);
            System.out.println("-------------");
        }

        List<User> users = userService.getAllUsers();
        iterator = users.iterator();
        System.out.println("\n=====All Users=====");
        while(iterator.hasNext()){
            User user = (User) iterator.next();
            userService.printUser(user, true);
        }
        mainMenu();
    }

}

//TODO Unit-tests
