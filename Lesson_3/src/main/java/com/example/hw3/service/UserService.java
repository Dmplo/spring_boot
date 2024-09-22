package com.example.hw3.service;

import com.example.hw3.domain.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class UserService {

    @Autowired
    private NotificationService notificationService;
    static int id = 1;

    public User createUser(String name, int age, String email) {
        User user = new User();
        user.setId(id++);
        user.setName(name);
        user.setAge(age);
        user.setEmail(email);

        notificationService.notifyUser("A new user has been created: ", user);
        return user;
    }
}