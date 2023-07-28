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

@Component("userDAO")
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class UserDAOImpl implements UserDAO{
    @Autowired
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    public UserDAOImpl() {
    }

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
            JOptionPane.showMessageDialog(null, e.getMessage(), "An Error Occurred trying to insert the element.", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateUser (Long userId, User user){
        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.update(user);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "An Error Occurred trying to insert the element.", JOptionPane.ERROR_MESSAGE);
        }
    }

    public User getUserById (Long userId){
        User user = null;
        try{
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            user = session.load(User.class, userId);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "FindById Error", JOptionPane.ERROR_MESSAGE);
        }
        return  user;
    }

    public User getUserByLogin(String login) {
        User user = null;
        try{
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("login", login));
            user = (User) criteria.uniqueResult();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "FindByLogin Error", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(null, e.getMessage(), "getAllUsers Error", JOptionPane.ERROR_MESSAGE);
        }
        return users;
    }

    public void deleteUser(User user){
        try {
            Session session = sessionFactory.getCurrentSession();
            session.getTransaction();
            session.delete(user);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "deleteUser Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public User getUserByTask(Task task){
        User user = null;
        try{
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            user = task.getUser();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "getUserByTask Error", JOptionPane.ERROR_MESSAGE);
        }
        return user;
    }

}
