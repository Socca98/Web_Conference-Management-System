package com.cms.controllers;

import com.cms.dto.*;
import com.cms.services.InitializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
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

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginUserDto loginUserDto) {
        TokenDto login = initializationService.login(loginUserDto);
        return ResponseEntity.ok(login);
    }

    @GetMapping("/user-information")
    public ResponseEntity<UserInformationDto> getUserInformation(@RequestParam(required = false) String conferenceId) {
        return ResponseEntity.ok(initializationService.getUserInformation(conferenceId));
    }

    @GetMapping("/token-information")
    public ResponseEntity<TokenInformation> getTokenInformation() {
        return ResponseEntity.ok(initializationService.getTokenInformation());
    }


}
