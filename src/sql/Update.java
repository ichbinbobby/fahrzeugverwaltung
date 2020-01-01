package sql;

import java.sql.*;

public class Update {
    public static void main(String args[]) {
        ConnectionWrapper cw = ConnectionWrapper.GetInstance();
    }
    public void insertBesitzer (int id){
        String sql = "DELETE FROM Besitzer WHERE ID=" + id;
        cw.ExecuteQuery(sql);
    }
    public void insertFahrzeug (String fahrzeug, int besitzerId){
        String sql = "INSERT INTO Fahrzeug (Name, BesitzerId) " +
                "VALUES ('" + fahrzeug + "', " + besitzerId + ");";
        cw.ExecuteQuery(sql);
    }
    public void updateFahrzeug(int besitzerId, int fahrzeugId) {
        String sql = "UPDATE FAHRZEUG SET BesitzerID=" + besitzerId +
                " WHERE FAHRZEUG.Id=" + fahrzeugId;
        cw.ExecuteQuery(sql);
    }
}
