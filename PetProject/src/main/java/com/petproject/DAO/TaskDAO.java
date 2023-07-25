package com.petproject.DAO;

import com.petproject.entity.Task;
import com.petproject.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TaskDAO {
    void addTask (Task task);
    void updateTask (Long taskId, Task task);
    Task getTaskById (Long taskId);
    List<Task> getAllTasks();
    void deleteTask (Task task);
    @Transactional
    List<Task> getTasksByUser (User user);
}
