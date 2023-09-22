package com.github.jrtb.javarushtelegrambot.command;

import org.junit.jupiter.api.DisplayName;

import static com.github.jrtb.javarushtelegrambot.command.UnknownCommand.UNKNOWN_MESSAGE;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Unit-level testing for UnknownCommand")
class UnknownCommandTest extends AbstractCommandTest {
    @Override
    String getCommandName() {
        return "/dgdsgsdgd";
    }

    @Override
    String getCommandMessage() {
        return UNKNOWN_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new UnknownCommand(sendBotMessageService);
    }
}