package com.petproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class Main {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Main.class);
        application.setApplicationStartup(new BufferingApplicationStartup(2048));
        application.run("--debug");
    }
}

//TODO Unit-tests
