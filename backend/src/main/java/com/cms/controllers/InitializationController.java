package com.cms.controllers;

import com.cms.dto.token.TokenDto;
import com.cms.dto.token.TokenInformation;
import com.cms.dto.user.LoginUserDto;
import com.cms.dto.user.RegisterUserDto;
import com.cms.dto.user.UserDto;
import com.cms.dto.user.UserInformationDto;
import com.cms.services.InitializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class InitializationController {

    @Autowired
    private InitializationService initializationService;

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

    @PostMapping("/invitation/{invitationId}")
    public ResponseEntity<UserDto> registerUserByInvitation(@PathVariable String invitationId,
                                                            @RequestBody RegisterUserDto registerUserDto) {
        UserDto userDto = initializationService.completeInvitation(invitationId, registerUserDto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

}


