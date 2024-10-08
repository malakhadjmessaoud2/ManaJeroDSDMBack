package com.example.backdsdm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableMongoRepositories(basePackages = "com.example.backdsdm.Repositories")

public class BackDsdmApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackDsdmApplication.class, args);
    }

}
