package sql;

import java.sql.*;

public class Select {
    public static void main( String args[] ) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:fahrzeugverwaltung.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            int besitzerID = 1; //selected from pane

            // show vehicles of one owner
            String sql = "SELECT NAME FROM FAHRZEUG WHERE BesitzerID=" + besitzerID;
            stmt.executeUpdate(sql);
            c.commit();

            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}
