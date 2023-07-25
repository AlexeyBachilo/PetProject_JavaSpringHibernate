package com.petproject.DAO;

import com.petproject.entity.Task;
import com.petproject.entity.User;
import com.petproject.service.TaskService;
import com.petproject.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO{
    public void addUser(User user){
        Session session = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "An Error Occurred trying to insert the element.", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
    }

    public void updateUser (Long userId, User user){
        Session session = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "An Error Occurred trying to insert the element.", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public User getUserById (Long userId){
        Session session = null;
        User user = null;
        try{
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            user = (User) session.load(User.class, userId);
            session.getTransaction().commit();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "FindById Error", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
        return  user;
    }

    public List<User> getAllUsers(){
        Session session = null;
        List users = new ArrayList<User>();
        try{
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            users = session.createCriteria(User.class).list();
            session.getTransaction().commit();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "getAllUsers Error", JOptionPane.OK_OPTION);
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        return users;
    }

    public void deleteUser(User user){
        Session session = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.getTransaction();
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "deleteUser Error", JOptionPane.OK_OPTION);
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }

    @Transactional
    public User getUserByTask(Task task){
        Session session = null;
        User user = null;
        try{
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            user = task.getUser();
            session.getTransaction().commit();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "getUserByTask Error", JOptionPane.OK_OPTION);
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        return user;
    }

    public void addTask(Task task){
        TaskService taskService = new TaskService();
        taskService.addTask(task);
    }

    public void deleteTask(Task task){
        TaskService taskService = new TaskService();
        taskService.deleteTask(task);
    }

    public void updateTask(Task task){
        TaskService taskService = new TaskService();
        taskService.updateTask(task);
    }
}
