package by.tc.epam.controller.Command.Impl;

import by.tc.epam.controller.Command.Command;
import by.tc.epam.service.LibraryService;
import by.tc.epam.service.exception.ServiceException;
import by.tc.epam.service.factory.ServiceFactory;

public class GetStatisticLessTwo implements Command {
    @Override
    public String execute(String request) {
        String response = null;

        ServiceFactory instance = ServiceFactory.getInstance();
        LibraryService libraryService = instance.getLibraryService();

        try {
            response =libraryService.lessThanOrEqualToTwo();
        } catch (ServiceException e) {
            response = "err stat less two";
            System.err.print(e.getMessage() + e.getCause());
        }

        return response;
    }
}
