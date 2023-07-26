package com.petproject.menu;

import com.petproject.entity.User;

import java.io.IOException;
import java.util.Scanner;

import static com.petproject.menu.UserMenu.selectUser;
import static com.petproject.menu.UserMenu.userMenu;
import static com.petproject.menu.TaskMenu.taskMenu;

public class MainMenu {
    public static void mainMenu() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=====Menu=====");
        System.out.println("1. User menu" + "\n2. Task menu\n");
        while(true){
            int choice = scanner.nextInt();
            switch (choice){
                case 1:{
                    userMenu();
                }
                case 2:{
                    User user = selectUser();
                    taskMenu(user);
                }
                default:{
                    System.out.println("There's no such option. Try again!\n");
                    continue;
                }
            }
        }

    }

}
