package com.github.jrtb.javarushtelegrambot.command;

import org.telegram.telegrambots.meta.api.objects.Update;

public class CommandUtils {

    public static String getChatId(Update update) {
        return update.getMessage().getChatId().toString();
    }

    public static String getMessage(Update update) {
        return update.getMessage().getText();
    }
}
