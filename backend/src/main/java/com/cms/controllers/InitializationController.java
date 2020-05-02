package com.cms.controllers;

import com.cms.dto.RegisterUserDto;
import com.cms.dto.UserDto;
import com.cms.services.InitializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class InitializationController {

    @Autowired
    InitializationService initializationService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody RegisterUserDto registerUserDto) {

        UserDto userDto = initializationService.register(registerUserDto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

}
