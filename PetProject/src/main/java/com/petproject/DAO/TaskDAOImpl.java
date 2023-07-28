package com.petproject.DAO;

import com.petproject.entity.Task;
import com.petproject.entity.User;
import jakarta.annotation.Resource;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Component("taskDAO")
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class TaskDAOImpl implements TaskDAO {
    @Autowired
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    public TaskDAOImpl() {
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addTask(Task task){
        try{
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.save(task);
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "addTask Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateTask (Long taskId, Task task){
        try{
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.update(task);
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "updateTsk Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Task getTaskById (Long taskId){
        Task task = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            task = session.load(Task.class, taskId);
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "getTaskById Error", JOptionPane.ERROR_MESSAGE);
        }
        return task;
    }

    public List<Task> getAllTasks(){
        List tasks = new ArrayList<>();
        try{
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            tasks = session.createCriteria(Task.class).list();
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "getAllTasks Error", JOptionPane.ERROR_MESSAGE);
        }
        return tasks;
    }

    public void deleteTask(Task task){
        try{
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.delete(task);
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "deleteTask Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<Task> getTasksByUser(User user){
        List tasks = new ArrayList<Task>();
        try{
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Task.class);
            criteria.add(Restrictions.eq("user",user));
            tasks = criteria.list();
            user.setTasks(tasks);
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "getTasksByUser Error", JOptionPane.ERROR_MESSAGE);
        }
        return tasks;
    }
}
