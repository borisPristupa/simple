package com.boris.simple.telegrambot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Arrays;

@Component
public class SimpleBot extends TelegramLongPollingBot {
    private static Logger logger = LoggerFactory.getLogger(SimpleBot.class);

    @Value("${bot.token}")
    private String token;
    @Value("${bot.username}")
    private String username;

    @Override
    public void onUpdateReceived(Update update) {
        logger.info("Received update: " + update.toString());
        if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText();
            if (text.equals("/start")) {
                SendMessage sender = new SendMessage();
                sender.setChatId(update.getMessage().getChatId())
                        .setText("Hello! My name is " + getBotUsername());
                try {
                    execute(sender);
                } catch (TelegramApiException e) {
                    logger.error(Arrays.toString(e.getStackTrace()));
                }
            }
        }
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    public SimpleBot(DefaultBotOptions options) {
        super(options);
    }
}
