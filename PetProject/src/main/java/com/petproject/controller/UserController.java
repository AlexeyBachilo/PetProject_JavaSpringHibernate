package com.petproject.controller;

import com.petproject.entity.User;
import com.petproject.service.UserService;
import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/petproject/main")
public class UserController {

    protected static Logger logger = LogManager.getLogger("UserControllerLogger");

    @Autowired
    UserService userService;

    @GetMapping(value = "/users")
    public String getUsers(Model model){
        logger.debug("Recieved request to show all users");
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "userMenu";
    }

    @GetMapping(value = "/users/add")
    public String getAdd(Model model){
        logger.debug("Recieved request to show add user page");
        model.addAttribute("userAttribute", new User());
        return "addUser";
    }

    @PostMapping(value = "/users/add")
    public String add(@ModelAttribute("userAttribute") User user){
        logger.debug("Recieved request to add new user");
        userService.addUser(user);
        return "addedUser";
    }

    @GetMapping(value = "/users/delete")
    public String delete(@RequestParam(value = "userId") Long userId, Model model){
        logger.debug("Recieved request to delete user");
        User user = userService.getUserById(userId);
        userService.deleteUser(user);
        model.addAttribute("user", user);
        return "deletedUser";
    }

    @GetMapping(value = "/users/update")
    public String getUpdate(@RequestParam(value = "userId") Long userId, Model model){
        logger.debug("Recieved request to show update user page");
        model.addAttribute("userAttribute", userService.getUserById(userId));
        return "updateUser";
    }

    @PostMapping(value = "/users/update")
    public String saveUpdate(@ModelAttribute("userAttribute") User user, @RequestParam(value = "userId") Long userId, Model model){
        logger.debug("Recieved request to update user");
        user.setUserId(userId);
        userService.updateUser(user);
        model.addAttribute("userId", userId);
        return "updatedUser";
    }
}
