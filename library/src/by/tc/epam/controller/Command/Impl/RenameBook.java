package by.tc.epam.controller.Command.Impl;

import by.tc.epam.controller.Command.Command;
import by.tc.epam.service.LibraryService;
import by.tc.epam.service.exception.ServiceException;
import by.tc.epam.service.factory.ServiceFactory;

public class RenameBook implements Command {
    @Override
    public String execute(String request) {

        String response = null;

        String[] work = request.split(" ");

        String oldName = work[1];
        String newName = work[2];

        ServiceFactory instance = ServiceFactory.getInstance();
        LibraryService libraryService = instance.getLibraryService();

        try {
            libraryService.renameBook(oldName,newName);
            response = "ok";
        } catch (ServiceException e) {
            response = "err rename";
            System.err.print(e.getMessage() + e.getCause());
        }

        return response;
    }
}
