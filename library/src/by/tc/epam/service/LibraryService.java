package by.tc.epam.service;

import by.tc.epam.service.exception.ServiceException;

public interface LibraryService {
    String moreThanOneBook() throws ServiceException;
    String lessThanOrEqualToTwo() throws ServiceException;
    void renameBook(String oldName, String newName) throws ServiceException;
}
