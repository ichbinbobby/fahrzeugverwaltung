package sql;

import java.sql.*;

public class Create {
    public static void main( String args[] ) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:fahrzeugverwaltung.db");
            System.out.println("Opened database successfully");

            stmt = c.createStatement();

            String sql = "CREATE TABLE Besitzer " +
                  "(Id INT PRIMARY KEY    NOT NULL," +
                  " Name           TEXT   NOT NULL)";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE Fahrzeug " +
                  "(Id INT PRIMARY KEY    NOT NULL," +
                  " Name           TEXT   NOT NULL," +
                  " BesitzerId     INT    NOT NULL," +
                  " FOREIGN KEY (BesitzerId) REFERENCES Besitzer(Id))";
            stmt.executeUpdate(sql);

            // Initial Besitzer table values
            sql = "INSERT INTO Besitzer (Id, Name) " +
                    "VALUES (1, 'General Grievous');";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Besitzer (Id, Name) " +
                    "VALUES (2, 'Listarte');";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Besitzer (Id, Name) " +
                    "VALUES (3, 'Angela');";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Besitzer (Id, Name) " +
                    "VALUES (4, 'Greta');";
            stmt.executeUpdate(sql);

            // Initial Fahrzeug table values
            sql = "INSERT INTO Fahrzeug (Id, Name, BesitzerId) " +
                    "VALUES (1, 'Wheel Bike', 1);";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Fahrzeug (Id, Name, BesitzerId) " +
                    "VALUES (2, 'Lista Wings', 2);";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Fahrzeug (Id, Name, BesitzerId) " +
                    "VALUES (3, 'Tigerpanzer', 3);";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Fahrzeug (Id, Name, BesitzerId) " +
                    "VALUES (4, 'Cybertruck', 4);";
            stmt.executeUpdate(sql);

            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
}
