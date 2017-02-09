package by.tc.epam.service.factory;

import by.tc.epam.service.LibraryService;
import by.tc.epam.service.impl.LibraryServiceImpl;

public class ServiceFactory {
    private final static ServiceFactory instance = new ServiceFactory();

    private final LibraryService libraryService = new LibraryServiceImpl();

    private ServiceFactory() {}

    public static ServiceFactory getInstance() {
        return instance;
    }

    public LibraryService getLibraryService() {
        return libraryService;
    }
}
