package com.boris.simple.telegrambot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.util.Arrays;

@SpringBootApplication
public class TelegrambotApplication {

    private static Logger logger = LoggerFactory.getLogger(TelegrambotApplication.class);

    @Value("${proxy.host}")
    private String proxyHost;
    @Value("${proxy.port}")
    private Integer proxyPort;

    public static void main(String[] args) {
        ApiContextInitializer.init();
        ConfigurableApplicationContext ctx = SpringApplication.run(TelegrambotApplication.class, args);
        try {
            ctx.getBean(TelegramBotsApi.class).registerBot(ctx.getBean(SimpleBot.class));
        } catch (TelegramApiRequestException e) {
            logger.error("Error: " + Arrays.toString(e.getStackTrace()));
            e.printStackTrace();
        }
    }

    @Bean
    public DefaultBotOptions getDefaultBotOptions() {
        DefaultBotOptions options = ApiContext.getInstance(DefaultBotOptions.class);
        options.setProxyHost(proxyHost);
        options.setProxyPort(proxyPort);
        options.setProxyType(DefaultBotOptions.ProxyType.SOCKS4);
        logger.info("Enabling SOCKS5 proxy " + proxyHost + ":" + proxyPort);
        return options;
    }

    @Bean
    public TelegramBotsApi getTelegramBotsApi() {
        return new TelegramBotsApi();
    }

}

