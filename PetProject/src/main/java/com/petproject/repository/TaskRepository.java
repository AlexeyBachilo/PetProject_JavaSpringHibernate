package com.petproject.repository;

import com.petproject.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM tasks t WHERE t.assigneduserid IN :id")
    List<Task> getTasksByUser(@Param("id") Long userId);
}