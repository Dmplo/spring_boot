package com.example.hw3.service;

import com.example.hw3.domain.User;
import com.example.hw3.exceptions.NotFoundException;
import com.example.hw3.repository.UserRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class DataProcessingService {

    private UserRepository repository;
    private NotificationService notificationService;

    public DataProcessingService(UserRepository repository, NotificationService notificationService) {
        this.repository = repository;
        this.notificationService = notificationService;
    }

    public List<User> sortUsersByAge() {
        notificationService.notifyUser("Sort users by age");
        return repository.getUsers().stream()
                .sorted(Comparator.comparing(User::getAge))
                .collect(Collectors.toList());
    }

    public List<User> filterUsersByAge(int age) {
        notificationService.notifyUser("Filter users by age > " + age);
        return repository.getUsers().stream()
                .filter(user -> user.getAge() > age)
                .collect(Collectors.toList());
    }

    public double calculateAverageAge() {
        notificationService.notifyUser("Calculating the average age of users");
        return repository.getUsers().stream()
                .mapToInt(User::getAge)
                .average()
                .orElse(0);
    }

    public void addUserToList(User user) {
        repository.getUsers().add(user);
    }

    public User getUserById(int id) {
        notificationService.notifyUser("Search user by id: " + id);
        return repository.getUsers().stream()
                .filter(user -> user.getId() == id).findFirst().orElseThrow(() -> {
                    notificationService.notifyUser("User with id: " + id + " not found");
                    return new NotFoundException();
                });
    }

    public List<String> getAllTasks() {
        notificationService.notifyUser("Show all the tasks");
        return List.of("sort", "filter", "calc");
    }

    public List<User> getAllUsers() {
        notificationService.notifyUser("Show all the users");
        return repository.getUsers();
    }
}
