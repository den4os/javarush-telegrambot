package com.github.jrtb.javarushtelegrambot.command;

import com.github.jrtb.javarushtelegrambot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.github.jrtb.javarushtelegrambot.command.CommandName.*;

public class HelpCommand implements Command{

    private final SendBotMessageService sendBotMessageService;

    public static final String HELP_MESSAGE = String.format("✨<b>Дотупные команды</b>✨\n\n"
                + "<b>Начать\\закончить работу с ботом</b>\n"
                + "%s - начать работу со мной\n"
                + "%s - приостановить работу со мной\n\n"

                + "Работа с подписчиками группы:\n"
                + "%s - подписаться на группу статей\n"
                + "%s - получить список групп, на который подписан\n\n"

                + "%s - получить помощь в работе со мной\n"
                + "%s - узнать количество пользователей JavaRush Telegram Bot\n",
            START.getCommandName(),
            STOP.getCommandName(),
            ADD_SUB_GROUP.getCommandName(),
            LIST_GROUP_SUB.getCommandName(),
            HELP.getCommandName(),
            STAT.getCommandName());

    public HelpCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), HELP_MESSAGE);
    }
}
