package com.petproject.controller;

import com.petproject.entity.User;
import com.petproject.service.UserService;
import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/petproject/admin/main")
public class UserController {

    protected static Logger logger = LogManager.getLogger("UserControllerLogger");

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    @Autowired
    UserService userService;

    @ModelAttribute("usersAttribute")
    public List<User> populateUsers(){
        return this.userService.getAllUsers();
    }

    @ModelAttribute("UDUserAttribute")
    public User UDUser(@RequestParam(value = "id", required = false, defaultValue = "1")Long id){
        return userService.getUserById(id);
    }

    @GetMapping(value = "/users")
    public String getUsers(){
        logger.debug("Recieved request to show all users");
        return "userMenu";
    }

    @GetMapping(value = "/users/delete")
    public String deleteUser(@RequestParam(value = "id")final Long userId, final ModelMap model){
        logger.debug("Recieved request to delete user");
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

    @PostMapping(value = "/users/update")
    public String updatedUser(@RequestParam(value = "id") final Long userId, final User user, final BindingResult bindingResult, final ModelMap model){
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
