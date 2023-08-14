package com.petproject.service;

import com.petproject.entity.Role;
import com.petproject.entity.Task;
import com.petproject.entity.User;

import com.petproject.repository.UserRepository;
import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class UserService {
    @Autowired
    @Lazy
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    @Lazy
    TaskService taskService;

    protected static Logger logger = LogManager.getLogger("UserServiceLogger");

    public void addUser(User user) {
        logger.debug("Adding user");
        user.setRoles(Collections.singleton(new Role(3L,"ROLE_USER")));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.saveAndFlush(user);
    }

    public void updateUser(User user) {
        logger.debug("Updating user");
        userRepository.saveAndFlush(user);
    }

    public void deleteUser(User user) {
        logger.debug("Deleting user");
        if(userRepository.findById(user.getUserId()).isPresent()){
            userRepository.delete(user);
        }
    }

    public User getUserById(Long id) {
        logger.debug("Getting user by Id");
        return (Optional.of(userRepository.getReferenceById(id))).orElse(new User());
    }

    public User getUserByLogin(String login) {
        logger.debug("Getting user by Login");
        return (Optional.of(userRepository.getUserByLogin(login))).orElse(new User());
    }

    public User getUserByEmail(String email){
        logger.debug("Getting user by email");
        return (Optional.of(userRepository.getUserByEmail(email))).orElse(new User());
    }

    public User getUserByTask(Task task) {
        logger.debug("Getting user by Task");
        return task.getUser();
    }

    public List<User> getAllUsers() {
        logger.debug("Getting all users");
        return userRepository.findAll();
    }

/*    public void makeAdmin(User user){
        logger.debug("Granting admin role to selected user");
        userRepository.makeAdmin(user.getUserId());
    }

    public void makeModer(User user){
        logger.debug("Granting moderator role to selected user");
        userRepository.makeModer(user.getUserId());
    }

    public void makeUser(User user){
        logger.debug("Granting user role to selected user");
        userRepository.makeUser(user.getUserId());
    }*/

    public void completeTask(Task task){
        logger.debug("Marking task as completed");
        task.setCompleted(!task.isCompleted());
        User user = getUserByTask(task);
        logger.debug("Adding taskpoint to user");
        user.setUserPoints(task.isCompleted() ? user.getUserPoints() + task.getTaskPoints() : user.getUserPoints() - task.getTaskPoints());
        taskService.updateTask(task);
        updateUser(user);
    }

    public void printUser(User user, boolean printTasks){
        logger.debug("Printing user");
        System.out.println(user.toString());
        if(printTasks){
            taskService.printTasksByUser(user);
            }
        }
}