package com.boris.simple.springjms;

import com.rabbitmq.jms.admin.RMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.jms.ConnectionFactory;

@EnableScheduling
@SpringBootApplication
@EnableJms
public class SpringjmsApplication {
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        SpringApplication.run(SpringjmsApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo() {
        return args -> {
            publishQuote();
            publishQuote();
            publishQuote();
        };
    }

    @Autowired
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Bean
    ConnectionFactory connectionFactory() {
        return new RMQConnectionFactory();
    }

    @Scheduled(fixedDelay = 2000)
    public void publishQuote() {
        jmsTemplate.setDefaultDestinationName("customDestination");
        jmsTemplate.send(session ->
                session.createObjectMessage(new Email("borya", "tanya", "simple topic", "hello, bro")));
    }

}

