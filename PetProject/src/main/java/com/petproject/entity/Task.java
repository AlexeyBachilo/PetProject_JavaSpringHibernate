package com.petproject.entity;


import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "tasks")
public class Task implements Serializable {
    @Id
    @SequenceGenerator(name = "task_seq", sequenceName = "task_task_id_seq", allocationSize = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_seq")
    @Column(name = "taskid")
    private Long taskId;
    @Column(name = "taskname")
    private String taskName;
    @Column(name = "taskdescription")
    private String taskDescription;
    @Column(name = "complete")
    private boolean isCompleted;
    @Column(name = "taskpoints")
    private int taskPoints;
    @Column(name = "deadline")
    private LocalDate deadline;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "assigneduserid")
    private User user;

    public Task(){}

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean getisCompleted() {
        return isCompleted;
    }

    public void setisCompleted(boolean completed) {
        isCompleted = completed;
    }

    public int getTaskPoints() {
        return taskPoints;
    }

    public void setTaskPoints(int taskPoints) {
        this.taskPoints = taskPoints;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return isCompleted == task.isCompleted && taskPoints == task.taskPoints && Objects.equals(taskId, task.taskId) && Objects.equals(taskName, task.taskName) && Objects.equals(taskDescription, task.taskDescription) && Objects.equals(user, task.user) && Objects.equals(deadline, task.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, taskName, taskDescription, user, isCompleted, taskPoints, deadline);
    }
}
