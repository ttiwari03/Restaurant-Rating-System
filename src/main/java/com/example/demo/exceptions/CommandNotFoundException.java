package com.example.demo.exceptions;

public class CommandNotFoundException extends Throwable {
    public CommandNotFoundException(String message) {
        super(message);
    }
}
