package by.tc.epam.controller;

import by.tc.epam.controller.Command.Command;
import by.tc.epam.controller.Command.CommandName;
import by.tc.epam.controller.Command.Impl.GetStatisticLessTwo;
import by.tc.epam.controller.Command.Impl.GetStatisticMoreThanOne;
import by.tc.epam.controller.Command.Impl.RenameBook;
import by.tc.epam.controller.Command.Impl.WrongRequest;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private final Map<CommandName, Command> repository = new HashMap<>();

    CommandProvider(){
        repository.put(CommandName.RENAME_BOOK, new RenameBook());
        repository.put(CommandName.GET_STATISTIC_MORE_THEN_ONE, new GetStatisticMoreThanOne());
        repository.put(CommandName.GET_STATISTIC_LESS_TWO, new GetStatisticLessTwo());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
    }

    Command getCommand(String name){
        CommandName commandName =null;
        Command command = null;
        try{
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        }catch(IllegalArgumentException | NullPointerException e){
            System.err.print("err command" + e.getCause());
            command = repository.get(CommandName.WRONG_REQUEST);
        }

        return command;
    }
}
