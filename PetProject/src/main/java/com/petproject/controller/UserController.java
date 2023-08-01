package com.petproject.controller;

import com.petproject.entity.User;
import com.petproject.service.UserService;
import org.apache.logging.log4j.core.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/main")
public class UserController {

    protected static Logger logger;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getUsers(Model model){
        logger.debug("Recieved request to show all users");
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "userMenu";
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.GET)
    public String getAdd(Model model){
        logger.debug("Recieved request to show add user page");
        model.addAttribute("userAttribute", new User());
        return "addUser";
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("userAttribute") User user){
        logger.debug("Recieved request to add new user");
        userService.addUser(user);
        return "addedUser";
    }

    @RequestMapping(value = "/users/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value = "userId") Long userId, Model model){
        logger.debug("Recieved request to delete user");
        User user = userService.getUserById(userId);
        userService.deleteUser(user);
        model.addAttribute("user", user);
        return "deletedUser";
    }

    @RequestMapping(value = "/users/update", method = RequestMethod.GET)
    public String getUpdate(@RequestParam(value = "userId") Long userId, Model model){
        logger.debug("Recieved request to show update user page");
        model.addAttribute("userAttribute", userService.getUserById(userId));
        return "updateUser";
    }

    @RequestMapping(value = "/users/update", method = RequestMethod.POST)
    public String saveUpdate(@ModelAttribute("userAttribute") User user, @RequestParam(value = "userId") Long userId, Model model){
        logger.debug("Recieved request to update user");
        user.setUserId(userId);
        userService.updateUser(user);
        model.addAttribute("userId", userId);
        return "updatedUser";
    }
}
