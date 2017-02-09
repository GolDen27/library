package by.tc.epam.controller.Command.Impl;

import by.tc.epam.controller.Command.Command;

public class WrongRequest implements Command {
    @Override
    public String execute(String request) {
        return "illegal command";
    }
}
