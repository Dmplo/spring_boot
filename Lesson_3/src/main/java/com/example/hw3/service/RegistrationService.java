package com.example.hw3.service;

import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class RegistrationService {
    UserService userService;
    DataProcessingService dataProcessingService;

    public RegistrationService(UserService userService, DataProcessingService dataProcessingService) {
        this.userService = userService;
        this.dataProcessingService = dataProcessingService;
    }
}
