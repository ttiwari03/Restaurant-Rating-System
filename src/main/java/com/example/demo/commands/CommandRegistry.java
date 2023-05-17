package com.example.demo.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CommandRegistry {
    private static final Map<String, ICommand> commands = new HashMap<>();

    public void registerCommand(String commandKeyword, ICommand command) {
        commands.putIfAbsent(commandKeyword,command);
    }

    public void unRegisterCommand(String commandKeyword) {
        commands.remove(commandKeyword);
    }

    private ICommand get(String commandName){
        return commands.get(commandName);
    }

    private List<String> parse(String input){
        List<String> tokens = new ArrayList<>();
        final String QUOTE = "\"";
        if (input.contains(QUOTE)) {
            int start = input.indexOf(QUOTE, 0);
            tokens = Arrays.stream(input.substring(0, start).split(" ")).collect(Collectors.toList());
            int end;
            List<String> quoteTokens=  new ArrayList<>();
            while (true) {
                end = input.indexOf(QUOTE, start + 1);
                if (end == -1) {
                    break;
                }
                quoteTokens.add(input.substring(start, end + 1));
                start = end + 1;
            }
            tokens.addAll(quoteTokens);
            tokens.addAll(Arrays.stream(input.substring(start).split(" ")).collect(Collectors.toList()));
        } else {
            tokens = Arrays.stream(input.split(" ")).collect(Collectors.toList());
        }
        return tokens;
    }


    public void invokeCommand(String input) {
        List<String> tokens = parse(input);
        ICommand command = get(tokens.get(0));
        if(command == null){
            // Handle Exception
            throw new RuntimeException("INVALID COMMAND 🛑");
        } 
        command.invoke(tokens);
        return;
    }
}
