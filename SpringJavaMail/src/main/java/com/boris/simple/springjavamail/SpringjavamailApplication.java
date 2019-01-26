package com.boris.simple.springjavamail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@SpringBootApplication
public class SpringjavamailApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringjavamailApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo() {
        return args -> {
//            sendSimpleMessage("boris.pristupa@gmail.com", "some topic", "hello from simple javamail");
            sendSimpleMessage("kuro-obi@yandex.ua", "some topic", "hello from simple javamail");
        };
    }

    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("boris.pristupa.dev@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

}

