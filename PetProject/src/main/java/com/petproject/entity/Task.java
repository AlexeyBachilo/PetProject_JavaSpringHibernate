package com.petproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks")
public class Task implements Serializable {
    @Id
    @SequenceGenerator(name = "task_seq", sequenceName = "task_task_id_seq", allocationSize = 0)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "task_seq")
    @Column(name = "taskid")
    private Long taskId;
    @Column(name = "taskname")
    private String taskName;
    @Column(name = "taskdescription")
    private String taskDescription;
    @Column(name = "complete")
    private boolean completed;
    @Column(name = "taskpoints")
    private int taskPoints = 0;
    @Column(name = "deadline")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate deadline;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "assigneduserid")
    private User user = null;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return completed == task.completed && taskPoints == task.taskPoints && Objects.equals(taskId, task.taskId) && Objects.equals(taskName, task.taskName) && Objects.equals(taskDescription, task.taskDescription) && Objects.equals(user, task.user) && Objects.equals(deadline, task.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, taskName, taskDescription, user, completed, taskPoints, deadline);
    }

    @Override
    public String toString(){
        return "Task Id: " + getTaskId() + "\nTask Name: " + getTaskName() + "\nTask Description: " + getTaskDescription()
                +"\nTask Points: " + getTaskPoints() + "\nIs Completed: " + ((isCompleted()) ? "Yes" : "No")
                +"\nDeadline: " + getDeadline();
    }
}
