package com.example.hw3.service;

import com.example.hw3.domain.User;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    public void notifyUser(String message, User user) {
        System.out.println(message + user.getName());
    }

    public void notifyUser(String message) {
        System.out.println(message);
    }
}