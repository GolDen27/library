package by.tc.epam.dao;

import by.tc.epam.dao.exception.DAOException;
import by.tc.epam.domain.Employee;

import java.util.Map;

public interface LibraryDAO {
    Map<Employee,Integer> moreThanOneBook() throws DAOException;
    Map<Employee,Integer> lessThanOrEqualToTwo() throws DAOException;
    void renameBook (String oldBrief, String newBrief) throws DAOException;
}
