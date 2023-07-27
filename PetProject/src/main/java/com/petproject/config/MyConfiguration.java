package com.petproject.config;

import com.petproject.DAO.TaskDAOImpl;
import com.petproject.DAO.UserDAOImpl;
import com.petproject.service.TaskService;
import com.petproject.service.UserService;
import org.springframework.context.annotation.*;

@Configuration
public class MyConfiguration {
    @Bean
    UserService userService(){
        return new UserService();
    }
    @Bean
    TaskService taskService(){
        return new TaskService();
    }
    @Bean
    UserDAOImpl userDAO(){
        return new UserDAOImpl();
    }
    @Bean
    TaskDAOImpl taskDAO(){
        return new TaskDAOImpl();
    }
}
