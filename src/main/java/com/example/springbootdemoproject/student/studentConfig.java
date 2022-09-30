package com.example.springbootdemoproject.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class studentConfig {
    @Bean
    CommandLineRunner commandLineRunner(studentRepository repository){
        return args -> {

            Student deniz = new Student(
				"deniz",
				"d@d",
				LocalDate.of(1998, Month.OCTOBER, 10)
			);

            Student arda = new Student(
				"arda",
				"a@a",
				LocalDate.of(1998, Month.OCTOBER, 8)
			);

            repository.saveAll(List.of(deniz,arda));
        };
    }
}
