package com.example.timemanagement.service;


import com.example.timemanagement.DTO.UserLoginDto;
import com.example.timemanagement.DTO.UserSignUpDto;
import com.example.timemanagement.entity.User;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity< User> createUser(UserSignUpDto userDto);

    User loginNewUser(UserLoginDto userLoginDto);

}
