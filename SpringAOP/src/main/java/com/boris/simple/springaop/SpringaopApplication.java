package com.boris.simple.springaop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class SpringaopApplication {

    private Logger logger = LoggerFactory.getLogger(SpringaopApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringaopApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(Business business) {
        return args -> {
            logger.info("--------------------------------------------------");
            logger.info("Executing demo application");
            logger.info("Calling Business.logic1() from demo");
            business.logic1();
            logger.info("");

            logger.info("Calling Business.logic2() from demo");
            business.logic2();
            logger.info("");

            logger.info("Conditional logging for Business.logic3() is " +
                    (business.isConditionalLoggingEnabled() ? "on" : "off"));
            logger.info("Calling Business.logic3() from demo");
            business.logic3();
            logger.info("");

            logger.info("Enabling conditional logging for Business.logic3()");
            business.setConditionalLoggingEnabled(true);
            logger.info("");

            logger.info("Conditional logging for Business.logic3() is " +
                    (business.isConditionalLoggingEnabled() ? "on" : "off"));
            logger.info("Calling Business.logic3() from demo");
            business.logic3();
        };
    }

}

