package com.petproject.controller;

import com.petproject.entity.User;
import com.petproject.service.UserService;
import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@Controller
@EnableWebMvc
@RequestMapping("/petproject/main")
public class UserController {

    protected static Logger logger = LogManager.getLogger("UserControllerLogger");

    @Autowired
    UserService userService;

    @ModelAttribute("userAttribute")
    public List<User> populateUsers(){
        return this.userService.getAllUsers();
    }

    @GetMapping(value = "/users")
    public String getUsers(){
        logger.debug("Recieved request to show all users");
        return "userMenu";
    }

    @GetMapping(value = "/users/add")
    public String addUser(){
        logger.debug("Recieved request to show add user page");
        return "addUser";
    }

    @PostMapping(value = "/users/add", params = {"Save"})
    public String addedUser(final User user, final BindingResult bindingResult, final ModelMap model){
        logger.debug("Recieved request to add new user");
        if(bindingResult.hasErrors()){
            return "addUser";
        }
        this.userService.addUser(user);
        model.clear();
        return "addedUser";
    }

    @GetMapping(value = "/users/delete")
    public String deleteUser(@RequestParam(value = "userId")final Long userId, final BindingResult bindingResult, final ModelMap model){
        logger.debug("Recieved request to delete user");
        if(bindingResult.hasErrors()){
            return "userMenu";
        }
        User user = this.userService.getUserById(userId);
        this.userService.deleteUser(user);
        model.clear();
        return "deletedUser";
    }

    @GetMapping(value = "/users/update")
    public String updateUser(){
        logger.debug("Recieved request to show update user page");
        return "updateUser";
    }

    @PostMapping(value = "/users/update", params = {"Save"})
    public String updatedUser(@RequestParam(value = "userId") final Long userId, final User user, final BindingResult bindingResult, final ModelMap model){
        logger.debug("Recieved request to update user");
        if(bindingResult.hasErrors()){
            return "updateUser";
        }
        user.setUserId(userId);
        this.userService.updateUser(user);
        model.addAttribute("userId", userId);
        return "updatedUser";
    }
}
