package com.petproject.entity;

import org.hibernate.annotations.*;
import org.springframework.scheduling.config.Task;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class User {
    private Long userId;
    private String login;
    private String firstName;
    private String lastName;
    private int userPoints;
    private Set tasks = new HashSet();

    public User(){}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastname) {
        this.lastName = lastname;
    }

    public int getUserPoints() {
        return userPoints;
    }

    public void setUserPoints(int userpoints) {
        this.userPoints = userpoints;
    }

    public Set getTasks() {
        return tasks;
    }

    public void setTasks(Set tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userPoints == user.userPoints && Objects.equals(userId, user.userId) && Objects.equals(login, user.login) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(tasks, user.tasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, login, firstName, lastName, userPoints, tasks);
    }
}
