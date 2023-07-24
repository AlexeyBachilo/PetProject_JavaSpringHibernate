package com.petproject.factory;

import com.petproject.DAO.TaskDAO;
import com.petproject.DAO.TaskDAOImpl;
import com.petproject.DAO.UserDAO;
import com.petproject.DAO.UserDAOImpl;

public class Factory {
    private static UserDAO userDAO = null;
    private static TaskDAO taskDAO = null;
    private static Factory instance = null;

    public static synchronized Factory getInstance() {
        if (instance == null){
            instance = new Factory();
        }
        return instance;
    }

    public static TaskDAO getTaskDAO() {
        if (taskDAO == null){
            taskDAO = new TaskDAOImpl();
        }
        return taskDAO;
    }

     public static UserDAO getUserDAO(){
        if (userDAO == null){
            userDAO = new UserDAOImpl();
        }
        return userDAO;
     }
}
