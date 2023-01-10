package com.example.timemanagement.controller;

import com.example.timemanagement.DTO.UserLoginDto;
import com.example.timemanagement.DTO.UserSignUpDto;
import com.example.timemanagement.entity.User;
import com.example.timemanagement.repository.UserRepository;
import com.example.timemanagement.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserControllerTest {


    @Autowired
    public UserService userService;




    @Test
    void createUser() {
        User user = new User(1l,"favour",
                "favourdaniel74@gamil.com","1234");

        UserSignUpDto actual= new UserSignUpDto("favour",
                "favourdaniel74@gamil.com","1234");
        UserService userService = Mockito.mock(UserService.class);
        when(userService.createUser(actual)).thenReturn(new ResponseEntity<>(user, HttpStatus.CREATED));
        ResponseEntity<User> responseEntity = userService.createUser(actual);
        assertEquals(user, responseEntity.getBody());

    }

    @Test
    void loginNewUser() {
        User user = new User(1l,"bukunmi","bukunmi@gmail.com","2345");
        UserLoginDto userLoginDto = new UserLoginDto("bukunmi@gmail.com","2345");
        UserService userService = Mockito.mock(UserService.class);
        when(userService.loginNewUser(userLoginDto)).thenReturn(user);
        User user2= userService.loginNewUser(userLoginDto);
        assertEquals(user,user2);

    }
}