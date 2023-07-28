package com.petproject.menu;

import com.petproject.service.TaskService;
import com.petproject.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
import java.util.Scanner;

import static com.petproject.menu.UserMenu.userMenu;
import static com.petproject.menu.TaskMenu.taskMenu;

@ComponentScan
public class MainMenu {


    public static void mainMenu(/*ApplicationContext ctx*/) throws IOException {
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
                    userMenu(/*ctx*/);
                }
                case 2 -> {
                    taskMenu(/*ctx*/);
                }
                default -> {
                    System.out.println("There's no such option. Try again!\n");
                }
            }
        }

    }

}
