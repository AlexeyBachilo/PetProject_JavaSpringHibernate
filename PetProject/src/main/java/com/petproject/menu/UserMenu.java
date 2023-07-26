package com.petproject.menu;

import com.petproject.entity.Task;
import com.petproject.entity.User;
import com.petproject.service.TaskService;
import com.petproject.service.UserService;
import jakarta.annotation.Resource;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import static com.petproject.menu.MainMenu.mainMenu;

public class UserMenu{
    @Resource(name="userService")
    static UserService userService;

    protected static void userMenu() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=====User Menu=====");
        System.out.println("1. Add user" + "\n2. Update user" + "\n3. Delete user" + "\n4. See All Users\n");
        while (true){
            int choice = scanner.nextInt();
            switch (choice){
                case 1:{
                    User user = new User();
                    System.out.println("Enter Login: ");
                    String newlogin = scanner.nextLine();
                    System.out.println("Enter First Name: ");
                    String newFirstName = scanner.nextLine();
                    System.out.println("Enter Last Name: ");
                    String newLastName = scanner.nextLine();
                    try {
                        user.setLogin(newlogin);
                        user.setFirstName(newFirstName);
                        user.setLastName(newLastName);
                        userService.addUser(user);
                        System.out.println("User added successfully!");
                    }catch (Exception e){
                        System.out.println("Failed to create a user!");
                    } finally {
                        mainMenu();
                    }
                    break;
                }
                case 2:{
                    User user = selectUser();
                    userService.printUser(user);
                    System.out.println("=====Update User=====");
                    System.out.println("1. Update Login" + "\n2. Update First Name" + "\n3. Update Last Name" + "\n4. Update Tasks");
                    while (true){
                        choice = scanner.nextInt();
                        switch (choice){
                            case 1:{
                                System.out.println("Old Login: " + user.getLogin());
                                System.out.println("New Login: ");
                                String newLogin = scanner.nextLine();
                                try {
                                    user.setLogin(newLogin);
                                    userService.updateUser(user);
                                }catch (Exception e){
                                    System.out.println("Failed to update the user!");
                                } finally {
                                    mainMenu();
                                    break;
                                }
                            }
                            case 2:{
                                System.out.println("Old First Name: " + user.getFirstName());
                                System.out.println("New First Name: ");
                                String newFirstName = scanner.nextLine();
                                try {
                                    user.setFirstName(newFirstName);
                                    userService.updateUser(user);
                                }catch (Exception e){
                                    System.out.println("Failed to update the user!");
                                }finally {
                                    mainMenu();
                                    break;
                                }
                            }
                            case 3:{
                                System.out.println("Old Last Name: " + user.getLastName());
                                System.out.println("New Last Name: ");
                                String newLastName = scanner.nextLine();
                                try {
                                    user.setLastName(newLastName);
                                    userService.updateUser(user);
                                }catch (Exception e){
                                    System.out.println("Failed to update the user!");
                                } finally {
                                    mainMenu();
                                    break;
                                }
                            }
                            case 4:{

                            }
                            default:{
                                System.out.println("There's no such option. Try again!");
                                continue;
                            }
                        }
                    }
                }
                case 3:{
                    User user = selectUser();
                    userService.printUser(user);
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
                    TaskService taskService = new TaskService();
                    List<User> users = userService.getAllUsers();
                    Iterator iterator = users.iterator();
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
                        mainMenu();
                        break;
                    }
                }
                default:{
                    System.out.println("There's no such option. Try again!\n");
                    continue;
                }
            }
        }
    }

    protected static User selectUser() throws IOException {
        Scanner scanner = new Scanner(System.in);
        User newUser;
        System.out.println("\n=====Select User=====");
        System.out.println("1. By Id" + "\n2. By Login\n");
        while (true){
            int choice = scanner.nextInt();
            switch (choice){
                case 1:{
                    System.out.println("Enter user Id: ");
                    while (true){
                        int id = scanner.nextInt();
                        try{
                            newUser = userService.getUserById(Long.valueOf(id));
                        }catch (Exception e){
                            System.out.println("No such user!");
                            continue;
                        }
                        return newUser;
                    }
                }
                case 2: {
                    System.out.println("Enter user Login: ");
                    while (true){
                        String login = scanner.nextLine();
                        try{
                            newUser = userService.getUserByLogin(login);
                        }catch (Exception e){
                            System.out.println("No such user!");
                            continue;
                        }
                        return newUser;
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
