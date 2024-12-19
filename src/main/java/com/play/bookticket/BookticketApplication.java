package com.play.bookticket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BookticketApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookticketApplication.class, args);
    }

}
