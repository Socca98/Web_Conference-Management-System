package com.cms.controllers;

import com.cms.model.Conference;
import com.cms.services.ConferencesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ConferencesController {
    private final ConferencesService conferenceService = new ConferencesService();


    @GetMapping(value = "/users/{conferenceId}")
    public ResponseEntity<Conference> getById(@PathVariable String conferenceId) {
        return ResponseEntity.ok(conferenceService.getById(conferenceId));
    }
}