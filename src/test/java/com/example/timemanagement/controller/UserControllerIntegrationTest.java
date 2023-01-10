package com.example.timemanagement.controller;

import com.example.timemanagement.DTO.UserLoginDto;
import com.example.timemanagement.DTO.UserSignUpDto;
import com.example.timemanagement.entity.User;
import com.example.timemanagement.repository.UserRepository;
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

import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.CoreMatchers.is;

@WebMvcTest
public class UserControllerIntegrationTest {

    @MockBean
    HttpSession httpSession;

    @Autowired
    MockMvc mockMvc;
    @MockBean
    UserController userController;

    @MockBean
    TaskController taskController;

    @MockBean
    UserService userService;

    @MockBean
    UserRepository userRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void shouldReturnNewlyCreatedUser() throws Exception {
        User user = new User(1l,"favour", "favourdaniel74@gamil.com","1234");
        UserSignUpDto userSignUpDto= new UserSignUpDto("favour", "favourdaniel74@gamil.com","1234");

        ResponseEntity<User> responseEntity = new ResponseEntity<>(user, HttpStatus.CREATED);
        when(userController.createUser(userSignUpDto)).thenReturn(responseEntity);

        mockMvc.perform(post("/user/createUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userSignUpDto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(user.getName())))
                .andExpect(jsonPath("$.email",is(user.getEmail())))
                .andExpect(jsonPath("$.password",is(user.getPassword())));

        verify(userController).createUser(userSignUpDto);

    }
    @Test
    public void shouldReturnLoggedInUser() throws Exception{
        User user = new User(1l,"bukunmi","bukunmi@gmail.com","2345");
        UserLoginDto userLoginDto = new UserLoginDto("bukunmi@gmail.com","2345");
        ResponseEntity<User> responseEntity = new ResponseEntity<>(user, HttpStatus.OK);

        when(userController.loginNewUser(userLoginDto,httpSession)).thenReturn(responseEntity);

        mockMvc.perform(post("/user/loginNewUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userLoginDto)))
                .andDo(print())
                .andExpect(status().isOk());

    }
}
