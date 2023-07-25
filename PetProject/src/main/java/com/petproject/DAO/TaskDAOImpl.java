package com.petproject.DAO;

import com.petproject.entity.Task;
import com.petproject.entity.User;
import com.petproject.util.HibernateSessionFactoryUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAOImpl implements TaskDAO{


    public void addTask(Task task){
        Session session = null;
        try{
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
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

    public void updateTask (Long taskId, Task task){
        Session session = null;
        try{
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
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

    public Task getTaskById (Long taskId){
        Session session = null;
        Task task = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            task = session.load(Task.class, taskId);
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

    public List<Task> getAllTasks(){
        Session session = null;
        List tasks = new ArrayList<Task>();
        try{
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            tasks = session.createCriteria(Task.class).list();
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

    public void deleteTask(Task task){
        Session session = null;
        try{
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
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

    @Transactional
    public List<Task> getTasksByUser(User user){
        Session session = null;
        List tasks = new ArrayList<Task>();
        try{
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Task.class);
            criteria.add(Restrictions.eq("user",user));
            tasks = criteria.list();
            user.setTasks(tasks);
            user.getTasks();
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
