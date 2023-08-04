package com.petproject.controller;

import org.apache.logging.log4j.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Controller
@EnableWebMvc
@RequestMapping("/petproject")
public class MainController {
    private static Logger logger = LogManager.getLogger("MainControllerLogger");

    @GetMapping("/main")
    public String mainMenu(){
        logger.debug("Accessing main menu");
        return "mainMenu";
    }


}
