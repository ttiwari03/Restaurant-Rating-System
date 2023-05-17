package com.example.demo.commands;

import java.util.List;
import com.example.demo.entities.User;
import com.example.demo.services.UserService;

public class AddUserCommand implements ICommand {
    private final UserService userService;

    public AddUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void invoke(List<String> tokens) {
        String name = tokens.get(1);
        User user = userService.register(name);
        System.out.println(user);
    }
    
}
