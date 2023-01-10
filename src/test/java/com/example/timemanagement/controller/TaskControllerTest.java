package com.example.timemanagement.controller;

import com.example.timemanagement.DTO.TaskDto;
import com.example.timemanagement.entity.Task;
import com.example.timemanagement.enums.Status;
import com.example.timemanagement.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.CoreMatchers.is;


@WebMvcTest
class TaskControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    TaskController taskController;

    @MockBean
    UserController userController;

    @MockBean
    UserService userService;

    @Test
    void createTask() throws Exception {
        TaskDto taskDto = new TaskDto("BEAT THE SINGERS",
                "User oraimo cord. I heard it's good for situations like this.", Status.COMPLETED);
        ResponseEntity<String> responseEntity = new ResponseEntity<>(
                "You have successfully created a task", HttpStatus.CREATED);

        when(taskController.createNewTask(taskDto)).thenReturn(responseEntity);

        mockMvc.perform(post("/task/createTask")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(taskDto)))
                .andDo(print())
                .andExpect(status().isCreated());

        verify(taskController).createNewTask(taskDto);
    }

    @Test
    void viewTasks() throws Exception {
        List<Task> tasks = Arrays.asList(
               Task.builder()
                       .id(1L)
                       .title("GROCERIES")
                       .description("No more PDAs")
                       .status(Status.PENDING)
                       .build(),
                Task.builder()
                        .id(2L)
                        .title("SWIMMING")
                        .description("Go swimming at constantial.")
                        .status(Status.PENDING)
                        .build(),
                Task.builder()
                        .id(3L)
                        .title("COOKING")
                        .description("Cook indomie with Angela.")
                        .status(Status.PENDING)
                        .build()
        );

        ResponseEntity<List<Task>> taskResponseEntity = new ResponseEntity<>(tasks, HttpStatus.OK);
        when(taskController.viewTasks()).thenReturn(taskResponseEntity);

        mockMvc.perform(get("/task/viewTasks"))
                .andDo(print())
                .andExpect(status().isOk());
        verify(taskController).viewTasks();
    }

    @Test
    void viewParticularTask() throws Exception {
        Task task = Task.builder()
                .id(1L)
                .title("COOKING")
                .description("Cook indomie with Angela.")
                .status(Status.PENDING)
                .build();
        ResponseEntity<Task> responseEntity = new ResponseEntity<>(task, HttpStatus.OK);
        when(taskController.viewParticularTask(1L)).thenReturn(responseEntity);

        mockMvc.perform(get("/task/viewParticularTask/{taskId}", 1L))
                .andDo(print())
                .andExpect(status().isOk());
//                .andExpect(jsonPath(""));

                verify(taskController).viewParticularTask(1L);
    }

    @Test
    void deleteTask() throws Exception {


        ResponseEntity<String> responseEntity = new ResponseEntity<>("you have successfully deleted a task",HttpStatus.OK);
        when(taskController.deleteTask(2L)).thenReturn(responseEntity);

        mockMvc.perform(delete("/task/deleteTask/{taskId}", 2L))
                .andDo(print())
                .andExpect(status().isOk());
    }
}