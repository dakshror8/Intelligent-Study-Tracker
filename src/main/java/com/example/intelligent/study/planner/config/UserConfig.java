package com.example.intelligent.study.planner.config;

import com.example.intelligent.study.planner.model.User;
import com.example.intelligent.study.planner.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository){
        return args -> {
            User aman = new User("Aman",
                    "aman.singh@gmail.com",
                    "123",
                    LocalDate.of(2000, DECEMBER, 5)
            );
            User daksh = new User("Daksh",
                    "daksh.singh@gmail.com",
                    "1234",
                    LocalDate.of(2001, DECEMBER, 9)
            );
            userRepository.saveAll(
                    List.of(aman, daksh)
            );
        };
    }
}
