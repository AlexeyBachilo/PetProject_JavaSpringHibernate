package com.petproject.menu;

import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
import java.util.Scanner;

import static com.petproject.menu.UserMenu.userMenu;
import static com.petproject.menu.TaskMenu.taskMenu;

@ComponentScan
public class MainMenu {


    public static void mainMenu() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=====Menu=====");
        System.out.println("""
                1. User menu
                2. Task menu
                """);
        while(true){
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> {
                    userMenu();
                }
                case 2 -> {
                    taskMenu();
                }
                default -> {
                    System.out.println("There's no such option. Try again!\n");
                }
            }
        }

    }

}
