package com.petproject.menu;

import com.petproject.entity.Task;
import com.petproject.entity.User;
import com.petproject.service.TaskService;
import com.petproject.service.UserService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import static com.petproject.menu.MainMenu.mainMenu;
import static com.petproject.menu.UserMenu.selectUser;

public class TaskMenu {
    protected static void taskMenu() throws IOException {
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();
        TaskService taskService = new TaskService();
        System.out.println("\n=====Task Menu=====");
        System.out.println("1. Add Task" + "\n2. Update Task" + "\n3. Delete Task" + "\n4. See All Tasks\n");
        while (true){
            int choice = scanner.nextInt();
            switch (choice){
                case 1:{
                    User user = selectUser();
                    Task task = new Task();
                    System.out.println("Enter Task Name: ");
                    String newTaskName = scanner.nextLine();
                    System.out.println("Enter Task Description: ");
                    String newTaskDescription = scanner.nextLine();
                    System.out.println("Enter Deadline [Format: YYYY-MM-DD HH:MM:SS]: ");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
                    LocalDate newDeadline = LocalDate.parse(scanner.nextLine(),formatter);
                    System.out.println("Enter Task Points: ");
                    int newTaskPoints = scanner.nextInt();
                    try {
                        task.setTaskName(newTaskName);
                        task.setTaskDescription(newTaskDescription);
                        task.setDeadline(newDeadline);
                        task.setUser(user);
                        task.setTaskPoints(newTaskPoints);
                        taskService.addTask(task);
                        System.out.println("Task added successfully!");
                    }catch (Exception e){
                        System.out.println("Failed to create a task!");
                    } finally {
                        mainMenu();
                        break;
                    }
                }
                case 2:{
                    Task toUpdate = selectTask();
                    updateTask(toUpdate);
                    break;
                }
                case 3:{
                    try{
                        userService.deleteUser(user);
                        System.out.println("User successfully deleted!");
                    } catch (Exception e){
                        System.out.println("Failed to delete the user!");
                    } finally {
                        mainMenu();
                    }
                    break;
                }
                case 4:{
                    List<Task> tasks = taskService.getAllTasks();
                    Iterator iterator = tasks.iterator();
                    System.out.println("\n=====All Tasks=====");
                    while(iterator.hasNext()){
                        Task task = (Task) iterator.next();
                        taskService.printTask(task);
                    }
                    mainMenu();
                    break;
                }
                default:{
                    System.out.println("There's no such option. Try again!\n");
                    continue;
                }
            }
        }
    }

    private static void updateTask(Task task) throws IOException {
        Scanner scanner = new Scanner(System.in);
        TaskService taskService = new TaskService();
        System.out.println("=====Update Task=====");
        System.out.println("1. Update Task Name" + "\n2. Update Task Description" + "\n3. Update Deadline"
                + "\n4. Update Task Points" + "\n5. Update Assigned User" + "\n6. Update Completion\n");
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
                        break;
                    }
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
                        break;
                    }
                }
                case 3:{
                    System.out.println("Old Deadline: " + task.getDeadline().toString());
                    System.out.println("New Deadline [Format: YYYY-MM-DD HH:MM:SS]: ");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
                    LocalDate newDeadline = LocalDate.parse(scanner.nextLine(),formatter);
                    try {
                        task.setDeadline(newDeadline);
                        taskService.updateTask(task);
                    }catch (Exception e){
                        System.out.println("Failed to update the task!");
                    } finally {
                        mainMenu();
                        break;
                    }
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
                        break;
                    }
                }
                case 5:{
                    System.out.println();
                }
                default:{
                    System.out.println("There's no such option. Try again!");
                    continue;
                }
            }
        }
    }

    private static Task selectTask(){
        Scanner scanner = new Scanner(System.in);
        TaskService taskService = new TaskService();
        Task newTask = null;
        System.out.println("\n=====Select Task=====");
        System.out.println("1. By Id" + "\n2. By Task Name\n");
        while (true){
            int choice = scanner.nextInt();
            switch (choice){
                case 1:{
                    System.out.println("Enter task Id: ");
                    while (true){
                        int id = scanner.nextInt();
                        try{
                            newTask = taskService.getTaskById(Long.valueOf(id));
                        }catch (Exception e){
                            System.out.println("No such task!");
                            continue;
                        }
                        return newTask;
                    }
                }
                case 2: {
                    System.out.println("Enter Task Name: ");
                    while (true){
                        String taskName = scanner.nextLine();
                        try{
                            List<Task> tasks = taskService.getAllTasks();
                            Iterator iterator = tasks.iterator();
                            while(iterator.hasNext()){
                                Task foundTask = (Task) iterator.next();
                                if(foundTask.getTaskName().equals(taskName)) newTask = foundTask;
                            }
                            if (newTask == null) throw new Exception();
                        }catch (Exception e){
                            System.out.println("No such task!");
                            continue;
                        }
                        return newTask;
                    }
                }
                default:{
                    System.out.println("There's no such option. Try again!\n");
                    continue;
                }
            }
        }

    }
}
