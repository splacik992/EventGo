package com.pali.eventgo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})
@EnableJpaRepositories
public class EventGoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventGoApplication.class, args);
    }


}
