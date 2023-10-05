package com.github.jrtb.javarushtelegrambot.command;

import com.github.jrtb.javarushtelegrambot.javarushclient.JavaRushGroupClient;
import com.github.jrtb.javarushtelegrambot.service.GroupSubService;
import com.github.jrtb.javarushtelegrambot.service.SendBotMessageService;
import com.github.jrtb.javarushtelegrambot.service.SendBotMessageServiceImpl;
import com.github.jrtb.javarushtelegrambot.service.TelegramUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Unit-level testing for CommandContainer")
class CommandContainerTest {

    private CommandContainer commandContainer;

    @BeforeEach
    public void init() {
        SendBotMessageService sendBotMessageService = Mockito.mock(SendBotMessageServiceImpl.class);
        TelegramUserService telegramUserService = Mockito.mock(TelegramUserService.class);
        JavaRushGroupClient javaRushGroupClient = Mockito.mock(JavaRushGroupClient.class);
        GroupSubService groupSubService = Mockito.mock(GroupSubService.class);
        commandContainer = new CommandContainer(sendBotMessageService,
                telegramUserService,
                javaRushGroupClient,
                groupSubService);
    }

    @Test
    public void shouldGetAllTheExistingCommands() {
        //when-then
        Arrays.stream(CommandName.values())
                .forEach(commandName -> {
                    Command command = commandContainer.retrieveCommand(commandName.getCommandName());
                    Assertions.assertNotEquals(UnknownCommand.class, command.getClass());
                });
    }


    @Test
    public void shouldReturnUnknownCommand() {
        //given
        String unknownCommand = "/sfdsdgha";

        //when
        Command command = commandContainer.retrieveCommand(unknownCommand);

        //then
        Assertions.assertEquals(UnknownCommand.class, command.getClass());
    }

}