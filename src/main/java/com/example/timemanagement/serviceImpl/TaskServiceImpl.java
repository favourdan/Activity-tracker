package com.example.timemanagement.serviceImpl;

import com.example.timemanagement.DTO.TaskDto;
import com.example.timemanagement.entity.Task;
import com.example.timemanagement.entity.User;
import com.example.timemanagement.enums.Status;
import com.example.timemanagement.exception.InvalidException;
import com.example.timemanagement.repository.TaskRepository;
import com.example.timemanagement.repository.UserRepository;
import com.example.timemanagement.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public Task createTask(TaskDto taskDto) {
        Long userId = (Long) httpSession.getAttribute("userId");
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found."));
        Task task = new Task();
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStatus(taskDto.getStatus());
        task.setCreatedAt(LocalDateTime.now());
        task.setUser(user);
        return taskRepository.save(task) ;
    }
    @Override
    public List<Task> viewTasks() {
        Long userId = (Long) httpSession.getAttribute("userId");
        System.out.println("USER ID: " + userId);
        return taskRepository.findTasksByUserId(userId);
    }

    @Override
    public Task viewParticularTask(Long taskId ) {
        Long userId = (Long) httpSession.getAttribute("userId");
        System.out.println("USERID VIEW: " + userId);
        return taskRepository.findTaskByUserId(userId,taskId);
    }

    @Override
    public Task viewTaskByStatus(Status status) {
        Long userId = (Long) httpSession.getAttribute("userId");
        return taskRepository.findTaskByStatus(userId,status.name());
    }

    public void deleteTask(Long taskId){
        Long userId = (Long) httpSession.getAttribute("userId");
        taskRepository.deleteTaskByUserId(userId);


 }

    @Override
    public Task updateTask(Long taskId, TaskDto taskDto) {
        Long userId = (Long) httpSession.getAttribute("userId");

        Task task = taskRepository.findById(taskId).orElseThrow(() -> new InvalidException("Task Not Found"));
        String title = taskDto.getTitle();
        String description = taskDto.getDescription();
        Status status = taskDto.getStatus();

        if(title != null && !title.isBlank())
            task.setTitle(title);
        if(description != null && !description.isBlank())
            task.setDescription(description);

        if(status != null) task.setStatus(status);

        return taskRepository.save(task);
    }
}
