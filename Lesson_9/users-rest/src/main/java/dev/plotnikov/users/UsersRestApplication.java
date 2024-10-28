package dev.plotnikov.users;

import dev.plotnikov.users.models.Role;
import dev.plotnikov.users.models.User;
import dev.plotnikov.users.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableDiscoveryClient
public class UsersRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsersRestApplication.class, args);
    }



}
