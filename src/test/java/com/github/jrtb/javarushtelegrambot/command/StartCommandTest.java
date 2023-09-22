package com.github.jrtb.javarushtelegrambot.command;

import org.junit.jupiter.api.DisplayName;

import static com.github.jrtb.javarushtelegrambot.command.CommandName.START;
import static com.github.jrtb.javarushtelegrambot.command.StartCommand.START_MESSAGE;

@DisplayName("Unit-Level testing for StartCommand")
class StartCommandTest extends AbstractCommandTest{
    @Override
    String getCommandName() {
        return START.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return START_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new StartCommand(sendBotMessageService);
    }
}
