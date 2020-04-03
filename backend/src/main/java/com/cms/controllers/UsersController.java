package com.cms.controllers;

import com.cms.model.User;
import com.cms.services.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UsersController {
    private final UsersService usersService = new UsersService();


    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> get() {
        return ResponseEntity.ok(usersService.get());
    }

}
