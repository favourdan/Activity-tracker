package com.example.timemanagement.repository;

import com.example.timemanagement.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query(value = "SELECT * FROM task WHERE user_id=?", nativeQuery = true)
    List<Task> findTasksByUserId (Long userId);


    @Query(value = "SELECT * FROM task WHERE user_id=? AND id=?", nativeQuery = true)
    Task findTaskByUserId (Long userId , Long taskId);

    @Query(value = "SELECT * FROM task WHERE user_id=?1 AND status=?2", nativeQuery = true)
    Task findTaskByStatus(Long userId, String status);

    @Query(value = "SELECT * FROM task WHERE user_id=?1 AND Id=?2", nativeQuery = true)
    Task deleteTaskByUserId(Long userId);

    @Query(value = "SELECT * FROM task WHERE  Id=?", nativeQuery = true)
    Task updateTaskByEmailAndPassword(Long taskId);

}
