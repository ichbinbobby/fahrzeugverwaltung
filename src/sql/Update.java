package sql;

import java.sql.*;

public class Update {
    ConnectionWrapper cw = ConnectionWrapper.GetInstance();
    public void addBesitzer (String name){
        String sql = "INSERT INTO Besitzer Name " +
                    "VALUES ('" + name + "');";
        cw.ExecuteQuery(sql);
    }
    public void addFahrzeug (int besitzerId, String typ){
        String sql = "INSERT INTO Fahrzeug Typ, BesitzerId " +
                    "VALUES ('" + typ + "," + besitzerId + "');";
        cw.ExecuteQuery(sql);
    }
    public void renameBesitzer (int besitzerId, String name){
        String sql = "UPDATE Besitzer SET Name=" + name +
                    " WHERE Id='" + besitzerId + "';";
        cw.ExecuteQuery(sql);
    }
    public void changeType(int fahrzeugId, String typ) {
        String sql = "UPDATE Fahrzeug SET Typ=" + typ +
                    " WHERE FAHRZEUG.Id=" + fahrzeugId;
        cw.ExecuteQuery(sql);
    }
}
