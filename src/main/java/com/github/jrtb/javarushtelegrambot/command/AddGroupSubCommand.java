package com.github.jrtb.javarushtelegrambot.command;

import com.github.jrtb.javarushtelegrambot.javarushclient.JavaRushGroupClient;
import com.github.jrtb.javarushtelegrambot.javarushclient.dto.GroupDiscussionInfo;
import com.github.jrtb.javarushtelegrambot.javarushclient.dto.GroupRequestArgs;
import com.github.jrtb.javarushtelegrambot.repository.entity.GroupSub;
import com.github.jrtb.javarushtelegrambot.service.GroupSubService;
import com.github.jrtb.javarushtelegrambot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.stream.Collectors;

import static com.github.jrtb.javarushtelegrambot.command.CommandUtils.getChatId;
import static com.github.jrtb.javarushtelegrambot.command.CommandUtils.getMessage;
import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.apache.commons.lang3.StringUtils.isNumeric;

public class AddGroupSubCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final JavaRushGroupClient groupClient;
    private final GroupSubService groupSubService;

    public AddGroupSubCommand(SendBotMessageService sendBotMessageService,
                              JavaRushGroupClient groupClient,
                              GroupSubService groupSubService) {
        this.sendBotMessageService = sendBotMessageService;
        this.groupClient = groupClient;
        this.groupSubService = groupSubService;
    }

    @Override
    public void execute(Update update) {
        if (getMessage(update).equalsIgnoreCase(CommandName.ADD_SUB_GROUP.getCommandName())) {
            sendGroupIdList(getChatId(update));
            return;
        }

        String groupId = getMessage(update).split(SPACE)[1];
        String chatId = getChatId(update);

        if (isNumeric(groupId)) {
            GroupDiscussionInfo groupById = groupClient.getGroupById(Integer.parseInt(chatId));

            if (isNull(groupById)) {
                sendGroupNotFound(chatId, groupId);
            }

            GroupSub savedGroupSub = groupSubService.save(chatId, groupById);
            sendBotMessageService.sendMessage(chatId, "Подписан на группу " + savedGroupSub.getTitle());
        } else {
            sendGroupNotFound(chatId, groupId);
        }
    }

    private void sendGroupNotFound(String chatId, String groupId) {
        String groupNotFoundMessage = "Нет группы с ID = \"%s\"";
        sendBotMessageService.sendMessage(chatId, String.format(groupNotFoundMessage, groupId));
    }

    private void sendGroupIdList(String chatId) {
        String groupIds = groupClient.getGroupList(GroupRequestArgs.builder().build()).stream()
                .map(group -> String.format("%s - %s \n", group.getTitle(), group.getId()))
                .collect(Collectors.joining());

        String message = "Чтобы подписаться на группу - передай комадну вместе с ID группы. \n" +
                "Например: /addGroupSub 16. \n\n" +
                "я подготовил список всех групп - выберай какую хочешь :) \n\n" +
                "имя группы - ID группы \n\n" +
                "%s";

        sendBotMessageService.sendMessage(chatId, String.format(message, groupIds));
    }
}
