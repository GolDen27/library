package by.tc.epam.dao.connection;

public class ConnectionFactory {

    private static final ConnectionFactory instance = new ConnectionFactory();

    private final ConnectionPool connectionPool = new ConnectionPoolImpl("jdbc:mysql://localhost:3306/eq","root","mops7568423");

    private ConnectionFactory() {}

    public static ConnectionFactory getInstance(){
        return instance;
    }

    public ConnectionPool getConnectionPool() {
        return connectionPool;
    }
}
