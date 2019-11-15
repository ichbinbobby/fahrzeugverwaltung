package sql;

import java.sql.*;

public class Update {
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
            int fahrzeugID = 1;

            // Change owner of vehicle
            String sql = "UPDATE FAHRZEUG SET BesitzerID=" + besitzerID +
                            " WHERE FAHRZEUG.ID=" + fahrzeugID;
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
