package sql;

import java.sql.*;

public class Select {
    ConnectionWrapper cw = ConnectionWrapper.GetInstance();
    public void showBesitzer (){
        String sql = "SELECT Name FROM Besitzer";
        cw.ExecuteQuery(sql);
    }
    public void showBesitzerId (int fahrzeugId){
        String sql = "SELECT Id FROM Besitzer " +
                    "INNER JOIN Fahrzeug" +
                    "ON Besitzer.Id=Fahrzeug.BesitzerId" +
                    "WHERE Fahrzeug.Id=" + fahrzeugId;
        cw.ExecuteQuery(sql);
    }
    public void showFahrzeug (){
        String sql = "SELECT Typ FROM Fahrzeug";
        cw.ExecuteQuery(sql);
    }
    public void showFahrzeug (int besitzerId){
        String sql = "SELECT Typ FROM Fahrzeug WHERE BesitzerId=" + besitzerId;
        cw.ExecuteQuery(sql);
    }
}
