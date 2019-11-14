package sql;

import java.sql.*;

public class SQLiteJDBC {
    public static void main( String args[] ) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:fahrzeugverwaltung.db");
            System.out.println("Opened database successfully");

            stmt = c.createStatement();

            String sql = "CREATE TABLE BESITZER " +
                  "(ID INT PRIMARY KEY    NOT NULL," +
                  " NAME           TEXT   NOT NULL)";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE FAHRZEUG " +
                  "(ID INT PRIMARY KEY    NOT NULL," +
                  " NAME           TEXT   NOT NULL," +
                  " BesitzerID     INT    NOT NULL," +
                  " FOREIGN KEY (BesitzerID) REFERENCES BESITZER(ID))";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO BESITZER (ID, NAME) " +
                    "VALUES (1, 'Sebastian');";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO BESITZER (ID, NAME) " +
                    "VALUES (2, 'Mark');";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO BESITZER (ID, NAME) " +
                    "VALUES (3, 'Angela');";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO BESITZER (ID, NAME) " +
                    "VALUES (4, 'Greta');";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO FAHRZEUG (ID, NAME, BesitzerID) " +
                    "VALUES (1, 'Ferrari', 1);";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO BESITZER (ID, NAME, BesitzerID) " +
                    "VALUES (2, 'Mercedes', 2);";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO BESITZER (ID, NAME, BesitzerID) " +
                    "VALUES (3, 'Tiger', 3);";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO BESITZER (ID, NAME, BesitzerID) " +
                    "VALUES (4, 'Tesla', 4);";
            stmt.executeUpdate(sql);

            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
}
