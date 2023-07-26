package com.petproject.DAO;

import com.petproject.entity.Task;
import com.petproject.entity.User;
import com.petproject.service.TaskService;
import jakarta.annotation.Resource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Transactional(propagation = Propagation.REQUIRES_NEW)
public class UserDAOImpl implements UserDAO{

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addUser(User user){
        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.save(user);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "An Error Occurred trying to insert the element.", JOptionPane.OK_OPTION);
        }
    }

    public void updateUser (Long userId, User user){
        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.update(user);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "An Error Occurred trying to insert the element.", JOptionPane.OK_OPTION);
        }
    }

    public User getUserById (Long userId){
        User user = null;
        try{
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            user = session.load(User.class, userId);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "FindById Error", JOptionPane.OK_OPTION);
        }
        return  user;
    }

    public List<User> getAllUsers(){
        List users = new ArrayList<User>();
        try{
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            users = session.createCriteria(User.class).list();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "getAllUsers Error", JOptionPane.OK_OPTION);
        }
        return users;
    }

    public void deleteUser(User user){
        try {
            Session session = sessionFactory.getCurrentSession();
            session.getTransaction();
            session.delete(user);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "deleteUser Error", JOptionPane.OK_OPTION);
        }
    }

    public User getUserByTask(Task task){
        User user = null;
        try{
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            user = task.getUser();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "getUserByTask Error", JOptionPane.OK_OPTION);
        }
        return user;
    }

}
