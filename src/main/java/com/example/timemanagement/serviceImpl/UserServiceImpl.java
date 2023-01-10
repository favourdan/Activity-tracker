package com.example.timemanagement.serviceImpl;

import com.example.timemanagement.DTO.UserLoginDto;
import com.example.timemanagement.DTO.UserSignUpDto;
import com.example.timemanagement.entity.User;
import com.example.timemanagement.exception.InvalidException;
import com.example.timemanagement.repository.UserRepository;
import com.example.timemanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public ResponseEntity<User> createUser(UserSignUpDto userSignUpDto) {
        User user = new User();
        user.setName(userSignUpDto.getName());
        user.setEmail(userSignUpDto.getEmail());
        user.setPassword(userSignUpDto.getPassword());
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }

    @Override
    public User loginNewUser(UserLoginDto userLoginDto) {
        User user = userRepository.findByEmailAndPassword(userLoginDto.getEmail(),userLoginDto.getPassword())
                .orElseThrow(() -> new InvalidException("Invalid credentials"));
        return user;

    }



}
