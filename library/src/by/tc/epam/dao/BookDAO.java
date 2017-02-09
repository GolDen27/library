package by.tc.epam.dao;

import by.tc.epam.dao.exception.DAOException;
import by.tc.epam.domain.Book;

import java.util.List;

public interface BookDAO {
    void addBook (Book book) throws DAOException;
    Book searchBook (String title) throws DAOException;
    List<Book> searchBookLike (String title) throws DAOException;
    void changeBook (String oldBookBrief, String newBookBrief) throws DAOException;
    void deleteBook (String brief) throws DAOException;
}
