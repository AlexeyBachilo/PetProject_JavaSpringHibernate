package com.petproject.application;

import com.petproject.entity.Task;
import com.petproject.entity.User;
import com.petproject.factory.Factory;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) throws SQLException {
        Collection tasks = Factory.getTaskDAO().getAllTasks();
        Iterator iterator = tasks.iterator();
        System.out.println("=====All Tasks=====");
        while (iterator.hasNext()){
            Task task = (Task) iterator.next();
            System.out.println("Task Name: " + task.getTaskName() + "\nTask Description: " + task.getTaskDescription()
            +"\nTask Points: " + task.getTaskPoints() + "\nIs Completed: " + ((task.getisCompleted()) ? "Yes" : "No")
            +"\nAssigned User: " + ((User)Factory.getUserDAO().getUserByTask(task)).getLogin());
            System.out.println("-------------");
        }

        Collection users = Factory.getUserDAO().getAllUsers();
        iterator = users.iterator();
        System.out.println("\n=====All Users=====");
        while(iterator.hasNext()){
            User user = (User) iterator.next();
            System.out.println("User Login: " + user.getLogin() + "\nFirst Name: " + user.getFirstName()
            +"\nLast Name: " + user.getLastName() + "\nCurrent Points: " + user.getUserPoints());
            Collection usertasks = Factory.getTaskDAO().getTasksByUser(user);
            Iterator iterator2 = usertasks.iterator();
            System.out.println("Current Tasks: ");
            while(iterator2.hasNext()) {
                Task task = (Task) iterator2.next();
                System.out.println("      Task Name: " + task.getTaskName() + " | Task Description: " + task.getTaskDescription()
                +" | Is Completed: " + ((task.getisCompleted()) ? "Yes" : "No"));
            }
        }
    }
}
