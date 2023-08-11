package com.petproject.controller;

import com.petproject.entity.User;
import com.petproject.service.UserService;
import org.apache.logging.log4j.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/petproject")
public class MainController {
    private static Logger logger = LogManager.getLogger("MainControllerLogger");

    @Autowired
    UserService userService;

    @ModelAttribute("newUserAttribute")
    public User newUser(){
        return new User();
    }

    @GetMapping("/admin/main")
    public String mainMenu(){
        logger.debug("Accessing main menu");
        return "adminMenu";
    }

    @GetMapping("/")
    public ModelAndView index(){
        logger.debug("Accessing index");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping("/login")
    public String logging(){
        logger.debug("Accessing logging page");
        return "login";
    }

    @GetMapping("/registration")
    public String registerring(){
        return "registration";
    }

    @PostMapping("/registration")
    public String registered(@Valid @ModelAttribute("newUserAttribute") User user, BindingResult bindingResult, Model model){
        User existingUserWithMail = userService.getUserByEmail(user.getEmail());
        User existingUserWithLogin = userService.getUserByLogin(user.getLogin());
        if(existingUserWithMail != null && existingUserWithMail.getEmail() != null && !existingUserWithMail.getEmail().isEmpty()){
            bindingResult.rejectValue("email", null, "There is already an account registered with the same email!");
        }
        if(existingUserWithLogin != null && existingUserWithLogin.getLogin() != null && !existingUserWithLogin.getLogin().isEmpty()){
            bindingResult.rejectValue("login", null, "There is already and account registered with the same login!");
        }
        if(!user.getPassword().equals(user.getPasswordConfirm())){
            bindingResult.rejectValue("password", null, "The passwords don't match.");
        }
        if(bindingResult.hasErrors()){
            model.addAttribute("user", user);
            return "registration";
        }
        userService.addUser(user);
        return "redirect:registration?success";
    }
}
