package ru.gb.hw;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.gb.hw.models.Role;
import ru.gb.hw.models.User;
import ru.gb.hw.service.UserService;

import java.util.ArrayList;

@SpringBootApplication
public class HwApplication {

	public static void main(String[] args) {
		SpringApplication.run(HwApplication.class, args);
	}

    @Bean
	CommandLineRunner run(UserService userService) {
        return args -> {

            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_MANAGER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));
            userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

            userService.save(new User(null, "John", "Travolta", "john", "1234", new ArrayList<>()));
            userService.save(new User(null, "Will", "Smith", "will", "1234", new ArrayList<>()));
            userService.save(new User(null, "Jim", "Carry", "jim", "1234", new ArrayList<>()));
            userService.save(new User(null, "Arnold", "Schwarzenegger", "arnold", "1234", new ArrayList<>()));

            userService.addRoleToUser("john", "ROLE_USER");
            userService.addRoleToUser("john", "ROLE_MANAGER");
            userService.addRoleToUser("will", "ROLE_MANAGER");
            userService.addRoleToUser("jim", "ROLE_ADMIN");
            userService.addRoleToUser("arnold", "ROLE_SUPER_ADMIN");
            userService.addRoleToUser("arnold", "ROLE_ADMIN");
            userService.addRoleToUser("arnold", "ROLE_USER");
        };
    }

}
