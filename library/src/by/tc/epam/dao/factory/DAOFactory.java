package by.tc.epam.dao.factory;

import by.tc.epam.dao.BookDAO;
import by.tc.epam.dao.LibraryDAO;
import by.tc.epam.dao.impl.BookDAOImpl;
import by.tc.epam.dao.impl.LibraryDAOImpl;

public class DAOFactory {
    private final static DAOFactory instance = new DAOFactory();

    private final BookDAO bookDAO = new BookDAOImpl();
    private final LibraryDAO libraryDAO = new LibraryDAOImpl();

    public LibraryDAO getLibraryDAO() {
        return libraryDAO;
    }

    private DAOFactory() {}

    public static DAOFactory getInstance() {
        return instance;
    }


    public BookDAO getBookDAO() {
        return bookDAO;
    }

}
