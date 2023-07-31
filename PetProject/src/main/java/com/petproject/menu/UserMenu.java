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
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import static com.petproject.menu.MainMenu.mainMenu;
import static com.petproject.menu.TaskMenu.selectTask;
import static com.petproject.menu.TaskMenu.updateTask;

@ComponentScan
public class UserMenu{
    @Autowired
    static UserService userService;
    @Autowired
    static TaskService taskService;


    protected static void userMenu() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=====User Menu=====");
        System.out.println("""
                1. Add user
                2. Update user
                3. Delete user
                4. See All Users
                """);
        while (true){
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> {
                    User user = new User();
                    System.out.println("Enter Login: \n");
                    String newLogin = scanner.nextLine();
                    System.out.println("Enter First Name: \n");
                    String newFirstName = scanner.nextLine();
                    System.out.println("Enter Last Name: \n");
                    String newLastName = scanner.nextLine();
                    try {
                        user.setLogin(newLogin);
                        user.setFirstName(newFirstName);
                        user.setLastName(newLastName);
                        userService.addUser(user);
                        System.out.println("User added successfully!");
                    } catch (Exception e) {
                        System.out.println("Failed to create a user!");
                    } finally {
                        mainMenu();
                        scanner.close();
                    }
                }
                case 2 -> {
                    User user = selectUser();
                    userService.printUser(user, true);
                    System.out.println("=====Update User=====");
                    System.out.println("""
                            1. Update Login
                            2. Update First Name
                            3. Update Last Name
                            4. Update Tasks""");
                    while (true) {
                        choice = scanner.nextInt();
                        switch (choice) {
                            case 1: {
                                System.out.println("Old Login: " + user.getLogin());
                                System.out.println("New Login: ");
                                String newLogin = scanner.nextLine();
                                try {
                                    user.setLogin(newLogin);
                                    userService.updateUser(user);
                                } catch (Exception e) {
                                    System.out.println("Failed to update the user!");
                                } finally {
                                    mainMenu();
                                }
                                break;
                            }
                            case 2: {
                                System.out.println("Old First Name: " + user.getFirstName());
                                System.out.println("New First Name: ");
                                String newFirstName = scanner.nextLine();
                                try {
                                    user.setFirstName(newFirstName);
                                    userService.updateUser(user);
                                } catch (Exception e) {
                                    System.out.println("Failed to update the user!");
                                } finally {
                                    mainMenu();
                                }
                                break;
                            }
                            case 3: {
                                System.out.println("Old Last Name: " + user.getLastName());
                                System.out.println("New Last Name: ");
                                String newLastName = scanner.nextLine();
                                try {
                                    user.setLastName(newLastName);
                                    userService.updateUser(user);
                                } catch (Exception e) {
                                    System.out.println("Failed to update the user!");
                                } finally {
                                    mainMenu();
                                }
                                break;
                            }
                            case 4: {
                                List<Task> userTasks = taskService.getTasksByUser(user);
                                for (Task task : userTasks) {
                                    taskService.printTask(task);
                                }
                                Task selectedTask = selectTask();
                                updateTask(selectedTask);
                            }
                            default: {
                                System.out.println("There's no such option. Try again!");
                            }
                        }
                    }
                }
                case 3 -> {
                    User user = selectUser();
                    userService.printUser(user, false);
                    try {
                        userService.deleteUser(user);
                        System.out.println("User successfully deleted!");
                    } catch (Exception e) {
                        System.out.println("Failed to delete the user!");
                    } finally {
                        mainMenu();
                    }
                }
                case 4 -> {
                    List<User> users = userService.getAllUsers();
                    Iterator<User> iterator = users.iterator();
                    System.out.println("\n=====All Users=====");
                    while (iterator.hasNext()) {
                        User user = iterator.next();
                        userService.printUser(user, true);
                        System.out.println("-------------");
                    }
                    mainMenu();
                }
                default -> {
                    System.out.println("There's no such option. Try again!\n");
                }
            }
        }
    }

    protected static User selectUser() throws IOException {
        Scanner scanner = new Scanner(System.in);
        User newUser;
        System.out.println("\n=====Select User=====");
        System.out.println("""
                1. By Id
                2. By Login
                """);
        while (true){
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.println("Enter user Id: ");
                    while (true) {
                        int id = scanner.nextInt();
                        try {
                            newUser = userService.getUserById((long) id);
                        } catch (Exception e) {
                            System.out.println("No such user!");
                            continue;
                        }
                        scanner.close();
                        return newUser;
                    }
                }
                case 2 -> {
                    System.out.println("Enter user Login: ");
                    while (true) {
                        String login = scanner.nextLine();
                        try {
                            newUser = userService.getUserByLogin(login);
                        } catch (Exception e) {
                            System.out.println("No such user!");
                            continue;
                        }
                        return newUser;
                    }
                }
                default -> {
                    System.out.println("There's no such option. Try again!\n");
                }
            }
        }

    }

}
