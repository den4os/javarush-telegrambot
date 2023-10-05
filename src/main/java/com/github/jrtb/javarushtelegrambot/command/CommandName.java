package com.github.jrtb.javarushtelegrambot.command;

public enum CommandName {

    START("/start"),
    STOP("/stop"),
    NO("nocommand"),
    HELP("/help"),
    STAT("/stat"),
    ADD_SUB_GROUP("/addgroupsub"),
    LIST_GROUP_SUB("/listgroupsub");

    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}
