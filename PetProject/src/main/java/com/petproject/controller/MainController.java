package com.petproject.controller;

import org.apache.logging.log4j.core.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    protected static Logger logger;

    @RequestMapping(value = "/main")
    public String mainMenu(){
        logger.debug("Accessing main menu");
        return "mainMenu";
    }


}
