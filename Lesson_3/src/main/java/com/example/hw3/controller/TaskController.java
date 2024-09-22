package com.example.hw3.controller;

import com.example.hw3.domain.User;
import com.example.hw3.service.DataProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private DataProcessingService dataProcessingService;

    @GetMapping
    public List<String> getAllTasks() {
        return dataProcessingService.getAllTasks();
    }

    @GetMapping("/sort")
    public List<User> sort() {
        return dataProcessingService.sortUsersByAge();
    }

    @GetMapping("/filter")
    public List<User> filter(@RequestParam(required = false) int age) {
        return dataProcessingService.filterUsersByAge(age);
    }

    @GetMapping("/calc")
    public Double calc() {
        return dataProcessingService.calculateAverageAge();
    }
}
