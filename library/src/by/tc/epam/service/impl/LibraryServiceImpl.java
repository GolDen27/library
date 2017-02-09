package by.tc.epam.service.impl;

import by.tc.epam.dao.BookDAO;
import by.tc.epam.dao.LibraryDAO;
import by.tc.epam.dao.exception.DAOException;
import by.tc.epam.dao.factory.DAOFactory;
import by.tc.epam.domain.Employee;
import by.tc.epam.service.LibraryService;
import by.tc.epam.service.exception.ServiceException;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Map;

public class LibraryServiceImpl implements LibraryService {
    @Override
    public String moreThanOneBook() throws ServiceException {
        String report = null;
        DAOFactory instance = DAOFactory.getInstance();
        LibraryDAO libraryDAO = instance.getLibraryDAO();
        Map<Employee,Integer> mapEmployee = null;
        try {
            mapEmployee = libraryDAO.moreThanOneBook();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        for (Map.Entry<Employee, Integer> entry : mapEmployee.entrySet())
        {
            report += entry.getKey().getName() + " - " + entry.getValue() + "\r\n";
        }

        return report;
    }

    @Override
    public String lessThanOrEqualToTwo() throws ServiceException {
        String report = null;
        DAOFactory instance = DAOFactory.getInstance();
        LibraryDAO libraryDAO = instance.getLibraryDAO();
        Map<Employee,Integer> mapEmployee = null;
        try {
            mapEmployee = libraryDAO.moreThanOneBook();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");

        for (Map.Entry<Employee, Integer> entry : mapEmployee.entrySet())
        {
            report += entry.getKey().getName() + " " + formatter.format(entry.getKey().getDateOfBirth()) + " - " + entry.getValue() + "\r\n";
        }

        return report;
    }

    @Override
    public void renameBook(String oldName, String newName) throws ServiceException {
        DAOFactory instance = DAOFactory.getInstance();
        BookDAO bookDAO = instance.getBookDAO();
        try {
            bookDAO.changeBook(oldName,newName);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
