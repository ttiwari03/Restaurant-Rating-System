package com.example.demo.commands;

import com.example.demo.exceptions.CommandNotFoundException;

public enum CommandKeyword {
    REGISTER_RESTAURANT("REGISTER_RESTAURANT"),
    REGISTER_USER("REGISTER_USER"),
    ADD_RATING("ADD_RATING"),
    ADD_REVIEW("ADD_REVIEW"),
    GET_REVIEWS("GET_REVIEWS"),
    GET_REVIEWS_FILTER_ORDER("GET_REVIEWS_FILTER_ORDER"),
    DESCRIBE_RESTAURANT("DESCRIBE_RESTAURANT"),
    LIST_RESTAURANT("LIST_RESTAURANT");

    private final String command;

    private CommandKeyword(String command) {
        this.command = command;
    }

    public CommandKeyword getCommand(String name) throws CommandNotFoundException {
        for (CommandKeyword keyword : CommandKeyword.values()) {
            if (keyword == CommandKeyword.valueOf(name)) {
                return keyword;
            }
        }
        throw new CommandNotFoundException("Command with given name " + name + " not found.");
    }
}
