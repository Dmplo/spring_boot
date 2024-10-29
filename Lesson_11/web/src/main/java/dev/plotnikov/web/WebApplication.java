package dev.plotnikov.web;

import dev.plotnikov.web.models.RoleDTO;
import dev.plotnikov.web.models.UserDTO;
import dev.plotnikov.web.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
@EnableFeignClients
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

//    @Bean
//    CommandLineRunner run(UserService userService) {
//        return args -> {
//            userService.saveRole(new RoleDTO(null, "ROLE_USER"));
//            userService.saveRole(new RoleDTO(null, "ROLE_MANAGER"));
//            userService.saveRole(new RoleDTO(null, "ROLE_ADMIN"));
//            userService.saveRole(new RoleDTO(null, "ROLE_SUPER_ADMIN"));
//
//            userService.save(new UserDTO(null, "John", "Travolta", "john", "1234", new ArrayList<>()));
//            userService.save(new UserDTO(null, "Will", "Smith", "will", "1234", new ArrayList<>()));
//            userService.save(new UserDTO(null, "Jim", "Carry", "jim", "1234", new ArrayList<>()));
//            userService.save(new UserDTO(null, "Arnold", "Schwarzenegger", "arnold", "1234", new ArrayList<>()));
//
//            userService.addRoleToUser("john", "ROLE_USER");
//            userService.addRoleToUser("john", "ROLE_MANAGER");
//            userService.addRoleToUser("will", "ROLE_MANAGER");
//            userService.addRoleToUser("jim", "ROLE_ADMIN");
//            userService.addRoleToUser("arnold", "ROLE_SUPER_ADMIN");
//            userService.addRoleToUser("arnold", "ROLE_ADMIN");
//            userService.addRoleToUser("arnold", "ROLE_USER");
//        };
//    }

}
