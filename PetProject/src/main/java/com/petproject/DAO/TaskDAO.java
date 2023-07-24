package com.petproject.DAO;

import com.petproject.entity.Task;
import com.petproject.entity.User;

import java.sql.SQLException;
import java.util.Collection;

public interface TaskDAO {
    public void addTask (Task task) throws SQLException;
    public void updateTask (Long taskId, Task task) throws SQLException;
    public Task getTaskById (Long taskId) throws SQLException;
    public Collection getAllTasks() throws SQLException;
    public void deleteTask (Task task) throws SQLException;
    public Collection getTasksByUser (User user) throws SQLException;
}
