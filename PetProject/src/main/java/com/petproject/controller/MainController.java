package com.petproject.controller;

import org.apache.logging.log4j.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/petproject")
public class MainController {
    private static Logger logger = LogManager.getLogger("MainControllerLogger");

    @GetMapping(value = "/main")
    public String mainMenu(){
        logger.debug("Accessing main menu");
        return "mainMenu";
    }


}
