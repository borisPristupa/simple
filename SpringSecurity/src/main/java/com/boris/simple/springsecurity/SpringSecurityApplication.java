package com.boris.simple.springsecurity;

import com.boris.simple.springsecurity.business.Business;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;

@SpringBootApplication
@EnableGlobalMethodSecurity(
        prePostEnabled = true, // enables @PreAuthorized and @PostAuthorized
        securedEnabled = true) // enables @Secured
public class SpringSecurityApplication {

    private Logger logger = LoggerFactory.getLogger(SpringSecurityApplication.class);

    @Bean
    public CommandLineRunner demo(Business business) {
        return args -> {
            SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_GLOBAL);
            Runnable checkUserAccess = () -> {
                try {
                    business.securedLogic();
                } catch (AccessDeniedException e) {
                    logger.error("Access to secured logic restricted for " + (
                            SecurityContextHolder.getContext().getAuthentication().getName()
                                    .isEmpty()
                                    ? "guest"
                                    : SecurityContextHolder.getContext().getAuthentication().getName()));
                }
                try {
                    business.verySecuredLogic();
                } catch (AccessDeniedException e) {
                    logger.error("Access to very secured logic restricted for " + (
                            SecurityContextHolder.getContext().getAuthentication().getName()
                                    .isEmpty()
                                    ? "guest"
                                    : SecurityContextHolder.getContext().getAuthentication().getName()));
                }
                try {
                    business.verySecuredAnother();
                } catch (AccessDeniedException e) {
                    logger.error("Access to another very secured logic restricted for " + (
                            SecurityContextHolder.getContext().getAuthentication().getName()
                                    .isEmpty()
                                    ? "guest"
                                    : SecurityContextHolder.getContext().getAuthentication().getName()));
                }
            };

            /*
            When checking access to Business.securedLogic(), Spring Security will do something like this:

                SecurityContextHolder.getContext().setAuthentication(
                    authenticationManager // the bean we declare below at getAuthenticationManager() will be used as default
                        .authenticate(
                            SecurityContextHolder.getContext().getAuthentication()
                        )
                );
             */
            System.out.println("---------------------------------------------------------------------");
            SecurityContextHolder.getContext()
                    .setAuthentication(new UsernamePasswordAuthenticationToken("cool user", "strong password")); // just a guest
            checkUserAccess.run(); // This user is very privileged

            System.out.println("---------------------------------------------------------------------");
            SecurityContextHolder.getContext()
                    .setAuthentication(new UsernamePasswordAuthenticationToken("not so cool", "weak password")); // just a guest
            checkUserAccess.run(); // This user has some privileges

            System.out.println("---------------------------------------------------------------------");
            SecurityContextHolder.getContext()
                    .setAuthentication(new UsernamePasswordAuthenticationToken("", "")); // just a guest
            checkUserAccess.run(); // This user is not privileged at all
        };
    }

    @Bean
    public AuthenticationManager getAuthenticationManager() {
        return authentication -> {

            if (authentication.getName().isEmpty()) // the user is just a guest
                return authentication;

            if (((String) authentication.getCredentials()).contains("password")) {

                if (authentication.getName().contains("user"))
                    return new UsernamePasswordAuthenticationToken(authentication.getName(),
                            authentication.getCredentials(),
                            Arrays.asList(new SimpleGrantedAuthority("ROLE_BIG_BOSS"),
                                    new SimpleGrantedAuthority("ROLE_COMMON")));

                else return new UsernamePasswordAuthenticationToken(authentication.getName(),
                        authentication.getCredentials(),
                        Arrays.asList(new SimpleGrantedAuthority("ROLE_LOSER"),
                                new SimpleGrantedAuthority("ROLE_COMMON")));
            }

            throw new BadCredentialsException("Well, you need a password if you're not a guest");
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }

}