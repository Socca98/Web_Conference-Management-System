package com.cms.controllers;

import com.cms.dto.user.UserDto;
import com.cms.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    /**
     * Returns the list with all users from the database.
     * @return list of Users as JSON, plus status code 200
     */
    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(usersService.getUsers());
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getUser(@PathVariable String username) {
        return ResponseEntity.ok(usersService.getUserDto(username));
    }
}
