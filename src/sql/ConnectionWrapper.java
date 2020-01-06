package sql;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionWrapper {

    private static ConnectionWrapper _instance;
    public static ConnectionWrapper GetInstance(){
        if(_instance == null){
            _instance = new ConnectionWrapper();
        }
        return _instance;
    }

    public void ExecuteQuery(String sql){
        try{
            Statement statement = connection.createStatement();
            statement.execute(sql);
            statement.close();
        }catch(Exception e){
            System.out.println(sql + " konnte nicht ausgeführt werden.");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    private Connection connection;

    private ConnectionWrapper(){
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
    protected void finalize() throws Throwable {
        super.finalize();
        connection.close();
    }
}
