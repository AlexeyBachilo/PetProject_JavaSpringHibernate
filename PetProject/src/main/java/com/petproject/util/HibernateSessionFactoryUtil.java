package com.petproject.util;

import com.petproject.entity.Task;
import com.petproject.entity.User;
import jakarta.annotation.Resource;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;


public class HibernateSessionFactoryUtil {
    @Resource(name = "sessionFactory")
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil () {
        try{
            if (sessionFactory == null){
                Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Task.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            }
        } catch (Throwable ex){
            System.err.println("Initial Session Factory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
}
