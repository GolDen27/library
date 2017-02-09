package by.tc.epam.dao.impl;

import by.tc.epam.dao.BookDAO;
import by.tc.epam.dao.LibraryDAO;
import by.tc.epam.dao.connection.ConnectionFactory;
import by.tc.epam.dao.exception.DAOException;
import by.tc.epam.dao.factory.DAOFactory;
import by.tc.epam.domain.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class LibraryDAOImpl implements LibraryDAO {
    private final String MORE_THAN_ONE = "select `employee`.`id`,`employee`.`name`, `employee`.`date_of_birth`,`employee`.`email`,`table`.`sum`\n"+
            "from `employee`\n"+
            "join\n"+
            "\t(select `book_has_employee`.`employee_id`, count(`book_has_employee`.`employee_id`) as `sum`\n"+
            "\tfrom `book_has_employee`\n"+
            "\tgroup by (`book_has_employee`.`employee_id`)) as `table`\n"+
            "on `table`.`employee_id`=`employee`.`id`\n"+
            "where sum>1\n" +
            "order by `sum`";

    private final String LESS_THAN_OR_EQUALS_TWO = "select `employee`.`id`,`employee`.`name`, `employee`.`date_of_birth`,`employee`.`email`,`table`.`sum`\n"+
            "from `employee`\n"+
            "join\n"+
            "\t(select `book_has_employee`.`employee_id`, count(`book_has_employee`.`employee_id`) as `sum`\n"+
            "\tfrom `book_has_employee`\n"+
            "\tgroup by (`book_has_employee`.`employee_id`)) as `table`\n"+
            "on `table`.`employee_id`=`employee`.`id`\n"+
            "where sum<=2\n" +
            "order by `sum`";

    @Override
    public Map<Employee, Integer> moreThanOneBook() throws DAOException {


        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        Connection connection = connectionFactory.getConnectionPool().retrieve();

        String sql = MORE_THAN_ONE;

        Map<Employee, Integer> mapEmployee = new LinkedHashMap<>();
        Employee employee = null;

        try (PreparedStatement st = connection.prepareStatement(sql)){

            try (ResultSet rs = st.executeQuery()) {

                while (rs.next()) {

                    employee = new Employee();

                    employee.setId(rs.getInt(1));
                    employee.setName(rs.getString(2));
                    employee.setDateOfBirth((rs.getDate(3)));
                    employee.setEmail(rs.getString(4));
                    int count = rs.getInt(5);

                    mapEmployee.put(employee,count);

                }
            }

        } catch (SQLException e) {

            connectionFactory.getConnectionPool().putback(connection);
            throw new DAOException("error search employee statistic");

        }

        connectionFactory.getConnectionPool().putback(connection);

        return mapEmployee;

    }

    @Override
    public Map<Employee, Integer> lessThanOrEqualToTwo() throws DAOException {


        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        Connection connection = connectionFactory.getConnectionPool().retrieve();

        String sql = LESS_THAN_OR_EQUALS_TWO;

        Map<Employee, Integer> mapEmployee = new LinkedHashMap<>();
        Employee employee = null;

        try (PreparedStatement st = connection.prepareStatement(sql)){

            try (ResultSet rs = st.executeQuery()) {

                while (rs.next()) {

                    employee = new Employee();

                    employee.setId(rs.getInt(1));
                    employee.setName(rs.getString(2));
                    employee.setDateOfBirth((rs.getDate(3)));
                    employee.setEmail(rs.getString(4));
                    int count = rs.getInt(5);

                    mapEmployee.put(employee,count);

                }
            }

        } catch (SQLException e) {

            connectionFactory.getConnectionPool().putback(connection);
            throw new DAOException("error search employee statistic");

        }

        connectionFactory.getConnectionPool().putback(connection);

        return mapEmployee;
    }

    @Override
    public void renameBook(String oldBrief, String newBrief) throws DAOException {
        DAOFactory instance = DAOFactory.getInstance();
        BookDAO bookDAO = instance.getBookDAO();
        bookDAO.changeBook(oldBrief,newBrief);
    }
}
