package ru.gb.hw.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "dbcmd")
@ConfigurationPropertiesScan
@PropertySource("classpath:db.properties")
@Data
public class DbConfig {
    private String findAll;
    private String findById;
    private String save;
    private String delete;
    private String update;
}
