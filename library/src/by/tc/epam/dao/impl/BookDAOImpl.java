package by.tc.epam.dao.impl;

import by.tc.epam.dao.BookDAO;
import by.tc.epam.dao.connection.ConnectionFactory;
import by.tc.epam.dao.exception.DAOException;
import by.tc.epam.domain.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {
    private final String ADD_BOOK_SQL = "insert into `book` (`brief``, `publish_year``, `author``) values (?,?,?)";
    private final String SEARCH_BOOK_SQL = "SELECT `id`, `brief`, `publish_year`, `author` from `book` where `brief`= ?";
    private final String SEARCH_BOOKS_SQL = "SELECT `id`, `brief`, `publish_year`, `author` from `book` where `brief` LIKE \'?\'";
    private final String CHANGE_BOOK_SQL = "update `book` set `brief`= ? where `brief`= ?";
    private final String DELETE_BOOK_SQL = "delete from `book` where `brief` = ?";
    @Override
    public void addBook(Book book) throws DAOException {

        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        Connection connection = connectionFactory.getConnectionPool().retrieve();

        String sql = ADD_BOOK_SQL;

        try (PreparedStatement st = connection.prepareStatement(sql)){

            st.setString(1,book.getBrief());
            st.setInt(2,book.getDateOfPublishing());
            st.setString(3,book.getAuthor());
            int a = st.executeUpdate();

        } catch (SQLException e) {

            connectionFactory.getConnectionPool().putback(connection);
            throw new DAOException("error insert book",e);

        }

        connectionFactory.getConnectionPool().putback(connection);

    }

    @Override
    public Book searchBook(String title) throws DAOException {

        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        Connection connection = connectionFactory.getConnectionPool().retrieve();


        String sql = SEARCH_BOOK_SQL;

        Book book = null;

        try (PreparedStatement st = connection.prepareStatement(sql)){

            st.setString(1,title);

            try (ResultSet rs = st.executeQuery()) {

                while (rs.next()) {

                    book = new Book();
                    book.setId(rs.getInt("id"));
                    book.setBrief(rs.getString("brief"));
                    book.setDateOfPublishing(rs.getInt("publish_year"));
                    book.setAuthor(rs.getString("author"));

                }
            }

        } catch (SQLException e) {

            connectionFactory.getConnectionPool().putback(connection);
            throw new DAOException("error search book",e);

        }

        connectionFactory.getConnectionPool().putback(connection);

        return book;

    }

    @Override
    public List<Book> searchBookLike(String title) throws DAOException {

        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        Connection connection = connectionFactory.getConnectionPool().retrieve();

        title = title.replace("*", "%");

        String sql = SEARCH_BOOKS_SQL;

        List<Book> books = new ArrayList<Book>();
        Book book = null;

        try (PreparedStatement st = connection.prepareStatement(sql)){

            st.setString(1,title);

            try (ResultSet rs = st.executeQuery()) {

                while (rs.next()) {

                    book = new Book();
                    book.setId(rs.getInt("id"));
                    book.setBrief(rs.getString("brief"));
                    book.setDateOfPublishing(rs.getInt("publish_year"));
                    book.setAuthor(rs.getString("author"));

                    books.add(book);

                }
            }

        } catch (SQLException e) {

            connectionFactory.getConnectionPool().putback(connection);
            throw new DAOException("error search books",e);

        }

        connectionFactory.getConnectionPool().putback(connection);

        return books;
    }

    @Override
    public void changeBook(String oldBookBrief, String newBookBrief) throws DAOException {

        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        Connection connection = connectionFactory.getConnectionPool().retrieve();

        String sql = CHANGE_BOOK_SQL;
        try (PreparedStatement st = connection.prepareStatement(sql)){

            st.setString(1,newBookBrief);
            st.setString(2,oldBookBrief);

            int a = st.executeUpdate();
        } catch (SQLException e) {
            connectionFactory.getConnectionPool().putback(connection);
            throw new DAOException("error update book",e);
        }

        connectionFactory.getConnectionPool().putback(connection);

    }

    @Override
    public void deleteBook(String brief) throws DAOException {


        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        Connection connection = connectionFactory.getConnectionPool().retrieve();

        String sql = DELETE_BOOK_SQL;

        try (PreparedStatement st = connection.prepareStatement(sql)){
            st.setString(1,brief);
            int a = st.executeUpdate();
        } catch (SQLException e) {
            connectionFactory.getConnectionPool().putback(connection);
            throw new DAOException("error delete book",e);
        }

        connectionFactory.getConnectionPool().putback(connection);

    }
}
