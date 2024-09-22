package com.example.hw3.controller;

import com.example.hw3.domain.User;
import com.example.hw3.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private RegistrationService service;

    @GetMapping("/all")
    public List<User> getAll() {
        return service.getDataProcessingService().getAllUsers();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Integer id) {
        return service.getDataProcessingService().getUserById(id);
    }

    @GetMapping("/create")
    public ResponseEntity<User> create(
            @RequestParam String name,
            @RequestParam Integer age,
            @RequestParam String email
    ) {
        User user = service.getUserService().createUser(name, age, email);
        service.getDataProcessingService().addUserToList(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
