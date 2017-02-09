package by.tc.epam.controller.Command.Impl;

import by.tc.epam.controller.Command.Command;
import by.tc.epam.service.LibraryService;
import by.tc.epam.service.exception.ServiceException;
import by.tc.epam.service.factory.ServiceFactory;

public class GetStatisticMoreThanOne implements Command {
    @Override
    public String execute(String request) {
        String response = null;

        ServiceFactory instance = ServiceFactory.getInstance();
        LibraryService libraryService = instance.getLibraryService();

        try {
            response =libraryService.moreThanOneBook();
        } catch (ServiceException e) {
            response = "err stat more one";
            System.err.print(e.getMessage() + e.getCause());
        }

        return response;
    }
}
