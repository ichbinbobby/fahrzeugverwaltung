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

    public ResultSet ExecuteQuery(String sql, SqlParameter... parameters) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);
        for (SqlParameter parameter : parameters) {
            parameter.addToStatement(statement);
        }
        ResultSet result = statement.executeQuery();
        return result;
    }

    public void Execute(String sql) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(sql);
        statement.close();
    }

    public void Execute(String sql, SqlParameter... parameters) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);
        for (SqlParameter parameter : parameters) {
            parameter.addToStatement(statement);
        }
        statement.execute();
        statement.close();
    }

    public ResultSet ExecuteQuery(String sql) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        return result;
    }

    public UpdateResult ExecuteUpdate(String sql, SqlParameter... parameters) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        for (SqlParameter parameter : parameters) {
            parameter.addToStatement(statement);
        }
        int rows = statement.executeUpdate();
        ResultSet generatedKeys = statement.getGeneratedKeys();
        return new UpdateResult(rows, generatedKeys);
    }

    public UpdateResult ExecuteUpdate(String sql) throws SQLException {
        Statement statement = connection.createStatement();
        int rows = statement.executeUpdate(sql);
        ResultSet generatedKeys = statement.getGeneratedKeys();
        return new UpdateResult(rows, generatedKeys);
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
