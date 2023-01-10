package com.example.timemanagement.controller;

import com.example.timemanagement.DTO.UserLoginDto;
import com.example.timemanagement.DTO.UserSignUpDto;
import com.example.timemanagement.entity.User;
import com.example.timemanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

  @PostMapping("/createUser")
  public ResponseEntity<User> createUser(@RequestBody UserSignUpDto userDto) {
      return userService.createUser(userDto);
  }

    @PostMapping("/loginNewUser")
    public ResponseEntity<User> loginNewUser(@RequestBody UserLoginDto userLoginDto,HttpSession httpSession) {
        User user = userService.loginNewUser(userLoginDto);
        httpSession.setAttribute("userId", user.getId());
        return new ResponseEntity<>(user, HttpStatus.OK);
}}