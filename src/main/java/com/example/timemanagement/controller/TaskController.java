package com.example.timemanagement.controller;

import com.example.timemanagement.DTO.TaskDto;
import com.example.timemanagement.DTO.TaskUpdateDto;
import com.example.timemanagement.entity.Task;
import com.example.timemanagement.enums.Status;
import com.example.timemanagement.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;


    @PostMapping("/createTask")
    public ResponseEntity<String> createNewTask(@RequestBody TaskDto taskDto) {
        taskService.createTask(taskDto);
        return new ResponseEntity<>("You have successfully created a task", HttpStatus.CREATED);
    }


    @GetMapping("/viewTasks")
    public ResponseEntity<List<Task>> viewTasks() {
        List<Task> tasks = taskService.viewTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }


   @GetMapping("/viewParticularTask/{taskId}")
    public ResponseEntity<Task> viewParticularTask (@PathVariable Long taskId){
        return new ResponseEntity<>(taskService.viewParticularTask(taskId),HttpStatus.OK);
   }

   @GetMapping("/viewPendingTask")
    public ResponseEntity<Task> viewPendingTask(){
        return new ResponseEntity<>(taskService.viewTaskByStatus(Status.PENDING),HttpStatus.OK);
   }
    @GetMapping("/viewCompletedTask")
    public ResponseEntity<Task> viewCompletedTask (){
        return new ResponseEntity<>(taskService.viewTaskByStatus(Status.COMPLETED),HttpStatus.OK);
    }

    @GetMapping("/viewInProgressTask")
    public ResponseEntity<Task> viewInProgressTask () {
        return new ResponseEntity<>(taskService.viewTaskByStatus(Status.IN_PROGRESS), HttpStatus.OK);

    }
    @DeleteMapping("/deleteTask/{taskId}")
      public ResponseEntity<String> deleteTask(@PathVariable Long taskId){
        taskService.deleteTask(taskId);
       return new ResponseEntity<>("DELETED", HttpStatus.OK);
  }
   @PutMapping("/updateTask/{taskId}")
    public ResponseEntity<Task> updateTask( @PathVariable Long taskId, @RequestBody TaskDto taskDto){
        Task task = taskService.updateTask(taskId, taskDto);
        return new ResponseEntity<>(task, HttpStatus.CREATED);

   }

}

