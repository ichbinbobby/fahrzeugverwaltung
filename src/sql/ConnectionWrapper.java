package sql;

import java.sql.*;

public class ConnectionWrapper implements AutoCloseable {

    private static ConnectionWrapper _instance;

    public static ConnectionWrapper GetInstance() {
        if (_instance == null) {
            _instance = new ConnectionWrapper();
        }
        return _instance;
    }

    public ResultSet ExecuteQuery(String sql) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        statement.close();
        return result;
    }

    public int ExecuteUpdate(String sql) throws SQLException {
        Statement statement = connection.createStatement();
        int rows = statement.executeUpdate(sql);
        statement.close();
        return rows;
    }

    private Connection connection;

    private ConnectionWrapper() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:fahrzeugverwaltung.db");
            connection.setAutoCommit(true);
            System.out.println("Opened database successfully");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    @Override
    public void close() throws SQLException {
        if (!connection.isClosed()) {
            connection.close();
        }
    }
}
