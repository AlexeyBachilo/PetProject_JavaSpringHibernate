package com.petproject.DAO;

import com.petproject.entity.Task;
import com.petproject.entity.User;
import com.petproject.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskDAOImpl implements TaskDAO{

    @Autowired
    private SessionFactory sessionFactory;

    public void addTask(Task task) throws SQLException{
        Session session = null;
        try{
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(task);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "addTask Error", JOptionPane.OK_OPTION);
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }

    public void updateTask (Long taskId, Task task) throws SQLException{
        Session session = null;
        try{
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(task);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "updateTsk Error", JOptionPane.OK_OPTION);
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }

    public Task getTaskById (Long taskId) throws SQLException{
        Session session = null;
        Task task = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            task = session.load(Task.class, taskId);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "getTaskById Error", JOptionPane.OK_OPTION);
        } finally {
            if(session.isOpen() && session != null){
                session.close();
            }
        }
        return task;
    }

    public List<Task> getAllTasks() throws SQLException {
        Session session = null;
        List tasks = new ArrayList<Task>();
        try{
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            tasks = session.createCriteria(Task.class).list();
            session.flush();
            session.getTransaction().commit();
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "getAllTasks Error", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
        return tasks;
    }

    public void deleteTask(Task task) throws SQLException{
        Session session = null;
        try{
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(task);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "deleteTask Error", JOptionPane.OK_OPTION);
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }

    public List<Task> getTasksByUser(User user) throws SQLException{
        Session session = null;
        List tasks = new ArrayList<Task>();
        try{
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            tasks = user.getTasks();
            session.flush();
            session.getTransaction().commit();
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "getTasksByUser Error", JOptionPane.OK_OPTION);
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        return tasks;
    }
}
