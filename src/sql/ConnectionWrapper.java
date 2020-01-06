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

    public ResultSet ExecuteQuery(String sql){
        try{
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            statement.close();
            return result;
        }catch(Exception e){
            System.out.println(sql + " konnte nicht ausgeführt werden.");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public int ExecuteUpdate(String sql){
        try{
            Statement statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
            statement.close();
            return rows;
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
    protected void finalize() {
        try {
            // finalize is deprecated
            super.finalize();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
