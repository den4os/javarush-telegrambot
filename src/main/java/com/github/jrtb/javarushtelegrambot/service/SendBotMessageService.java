package com.github.jrtb.javarushtelegrambot.service;

public interface SendBotMessageService {

    void sendMessage(String chatId, String message);
}
