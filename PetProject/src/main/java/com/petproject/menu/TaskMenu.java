package com.petproject.menu;

import com.petproject.entity.Task;
import com.petproject.entity.User;
import com.petproject.service.TaskService;
import com.petproject.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import static com.petproject.menu.MainMenu.mainMenu;
import static com.petproject.menu.UserMenu.selectUser;

@ComponentScan
public class TaskMenu {
    @Autowired
    static UserService userService;
    @Autowired
    static TaskService taskService;

    protected static void taskMenu() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=====Task Menu=====");
        System.out.println("""
                1. Add Task
                2. Update Task
                3. Delete Task
                4. See All Tasks
                """);
        while (true){
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> {
                    User user = selectUser();
                    Task task = new Task();
                    System.out.println("Enter Task Name: ");
                    String newTaskName = scanner.nextLine();
                    System.out.println("Enter Task Description: ");
                    String newTaskDescription = scanner.nextLine();
                    System.out.println("Enter Deadline [Format: YYYY-MM-DD HH:MM:SS]: ");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
                    String newDL = scanner.nextLine();
                    Date newDeadline = (Date) formatter.parse(newDL);
                    System.out.println("Enter Task Points: ");
                    int newTaskPoints = scanner.nextInt();
                    User newUser = selectUser();
                    try {
                        task.setTaskName(newTaskName);
                        task.setTaskDescription(newTaskDescription);
                        task.setDeadline(newDeadline);
                        task.setUser(user);
                        task.setTaskPoints(newTaskPoints);
                        task.setUser(newUser);
                        taskService.addTask(task);
                        System.out.println("Task added successfully!");
                    } catch (Exception e) {
                        System.out.println("Failed to create a task!");
                    } finally {
                        mainMenu();
                    }
                }
                case 2 -> {
                    Task toUpdate = selectTask();
                    updateTask(toUpdate);
                }
                case 3 -> {
                    try {
                        Task task = selectTask();
                        taskService.deleteTask(task);
                        System.out.println("Task successfully deleted!");
                    } catch (Exception e) {
                        System.out.println("Failed to delete the task!");
                    } finally {
                        mainMenu();
                    }
                }
                case 4 -> {
                    List<Task> tasks = taskService.getAllTasks();
                    Iterator<Task> iterator = tasks.iterator();
                    System.out.println("\n=====All Tasks=====");
                    while (iterator.hasNext()) {
                        Task task = (Task) iterator.next();
                        taskService.printTask(task);
                    }
                    mainMenu();
                }
                default -> {
                    System.out.println("There's no such option. Try again!\n");
                }
            }
        }
    }

    protected static void updateTask(Task task) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=====Update Task=====");
        System.out.println("""
                1. Update Task Name
                2. Update Task Description
                3. Update Deadline
                4. Update Task Points
                5. Update Assigned User
                6. Update Completion
                """);
        while (true){
            int choice = scanner.nextInt();
            switch (choice){
                case 1:{
                    System.out.println("Old Task Name: " + task.getTaskName());
                    System.out.println("New Task Name: ");
                    String newTaskName = scanner.nextLine();
                    try {
                        task.setTaskName(newTaskName);
                        taskService.updateTask(task);
                    }catch (Exception e){
                        System.out.println("Failed to update the task!");
                    } finally {
                        mainMenu();
                    }
                    break;
                }
                case 2:{
                    System.out.println("Old Task Description: " + task.getTaskDescription());
                    System.out.println("New Task Description: ");
                    String newTaskDescription = scanner.nextLine();
                    try {
                        task.setTaskDescription(newTaskDescription);
                        taskService.updateTask(task);
                    }catch (Exception e){
                        System.out.println("Failed to update the task!");
                    }finally {
                        mainMenu();
                    }
                    break;
                }
                case 3:{
                    System.out.println("Old Deadline: " + task.getDeadline().toString());
                    System.out.println("New Deadline [Format: YYYY-MM-DD HH:MM:SS]: ");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
                    Date newDeadline = (Date) formatter.parse(scanner.nextLine());
                    try {
                        task.setDeadline(newDeadline);
                        taskService.updateTask(task);
                    }catch (Exception e){
                        System.out.println("Failed to update the task!");
                    } finally {
                        mainMenu();
                    }
                    break;
                }
                case 4:{
                    System.out.println("Old Task Points Value: " + task.getTaskPoints());
                    System.out.println("New Task Points Value: ");
                    int newTaskPoints = scanner.nextInt();
                    try{
                        task.setTaskPoints(newTaskPoints);
                        taskService.updateTask(task);
                    }catch (Exception e){
                        System.out.println("Failed to update the task!");
                    }finally {
                        mainMenu();
                    }
                    break;
                }
                case 5:{
                    System.out.println("Old Assigned User Login: " + task.getUser().getLogin());
                    System.out.println("New Assigned User Login: ");
                    while(true){
                        String newUserLogin = scanner.nextLine();
                        User newUser = userService.getUserByLogin(newUserLogin);
                        if (newUser != null){
                            task.setUser(newUser);
                            taskService.updateTask(task);
                            break;
                        }
                        else{
                            System.out.println("There's no such user. Try again!");
                        }
                    }
                    break;
                }
                case 6:{
                    boolean completion = task.getisCompleted();
                    System.out.println("Is task completed: " + (completion ? "Yes" : "No"));
                    if(!completion){
                        System.out.println("Mark task as completed? Y/N: ");
                        String answer = scanner.nextLine();
                        if ("Y".equals(answer)) {
                            userService.completeTask(task);
                        }
                        mainMenu();
                    }
                    else {
                        System.out.println("Mark task as uncompleted? Y/N");
                        String answer = scanner.nextLine();
                        if ("Y".equals(answer)) {
                            userService.completeTask(task);
                        }
                        mainMenu();
                    }
                }
                default:{
                    System.out.println("There's no such option. Try again!");
                }
            }
        }
    }

    protected static Task selectTask(){
        Scanner scanner = new Scanner(System.in);
        TaskService taskService = new TaskService();
        Task newTask = null;
        System.out.println("\n=====Select Task=====");
        System.out.println("""
                1. By Id
                2. By Task Name
                """);
        while (true){
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.println("Enter task Id: ");
                    while (true) {
                        int id = scanner.nextInt();
                        try {
                            newTask = taskService.getTaskById((long) id);
                        } catch (Exception e) {
                            System.out.println("No such task!");
                            continue;
                        }
                        return newTask;
                    }
                }
                case 2 -> {
                    System.out.println("Enter Task Name: ");
                    while (true) {
                        String taskName = scanner.nextLine();
                        try {
                            List<Task> tasks = taskService.getAllTasks();
                            for (Task foundTask : tasks) {
                                if (foundTask.getTaskName().equals(taskName)) newTask = foundTask;
                            }
                            if (newTask == null) throw new Exception();
                        } catch (Exception e) {
                            System.out.println("No such task!");
                            continue;
                        }
                        return newTask;
                    }
                }
                default -> {
                    System.out.println("There's no such option. Try again!\n");
                }
            }
        }

    }
}
