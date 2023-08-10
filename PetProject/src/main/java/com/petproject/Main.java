package com.petproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring6.SpringTemplateEngine;

@SpringBootApplication
@ComponentScan
@EnableJpaRepositories
@EnableTransactionManagement
public class Main {
    @Autowired
    static SpringTemplateEngine templateEngine = new SpringTemplateEngine();

    public static void main(String[] args) {
        templateEngine.addDialect(new Java8TimeDialect());
        SpringApplication application = new SpringApplication(Main.class);
        application.setApplicationStartup(new BufferingApplicationStartup(2048));
        application.run("--debug");
    }
}

//TODO Unit-tests
