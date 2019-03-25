package com.boris.simple.jabber;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JabberApplication {
    public static void main(String[] args) {
        SpringApplication.run(JabberApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(JabberSender sender) {
        return args -> sender.send("boris_pristupa@jabber.ru", "I, Jabber");
    }
}
