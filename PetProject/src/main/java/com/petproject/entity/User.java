package com.petproject.entity;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @SequenceGenerator(name = "user_seq", sequenceName = "user_user_id_seq", allocationSize = 0)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_seq")
    @Column(name = "userid", unique = true, nullable = false)
    private Long userId;
    @Column(name = "login")
    private String login;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "userpoints")
    private int userPoints = 0;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Task> tasks = null;

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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
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

    @Override
    public String toString(){
        return "User Id: " + getUserId() + "\nUser Login: " + getLogin() + "\nFirst Name: " + getFirstName()
                +"\nLast Name: " + getLastName() + "\nCurrent Points: " + getUserPoints();
    }
}
