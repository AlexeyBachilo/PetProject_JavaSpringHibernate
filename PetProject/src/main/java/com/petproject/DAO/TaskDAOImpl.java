package com.petproject.DAO;

import com.petproject.entity.Task;
import com.petproject.entity.User;
import com.petproject.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.xml.*;

public class TaskDAOImpl implements TaskDAO{
    public void addTask(Task task) throws SQLException{
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(task);
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
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(task);
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
            session = HibernateUtil.getSessionFactory().openSession();
            task = (Task) session.load(Task.class, taskId);
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "getTaskById Error", JOptionPane.OK_OPTION);
        } finally {
            if(session.isOpen() && session != null){
                session.close();
            }
        }
        return task;
    }

    public Collection getAllTasks() throws SQLException {
        Session session = null;
        List tasks = new ArrayList<Task>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            tasks = session.createCriteria(Task.class).list();
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
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(task);
            session.getTransaction().commit();
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "deleteTask Error", JOptionPane.OK_OPTION);
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }

    public Collection getTasksByUser(User user) throws SQLException{
        Session session = null;
        List tasks = new ArrayList<Task>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Long userId = user.getUserId();
            Query query = session.createQuery(
                    "from Task where userid = :userId ").setLong("userId",userId);
            tasks = (List<Task>) query.list();
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
