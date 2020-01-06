package sql;

import java.sql.*;

public class Create {
    ConnectionWrapper cw = ConnectionWrapper.GetInstance();

    public static void main( String args[] ) {
        ConnectionWrapper cw = ConnectionWrapper.GetInstance();

        String sql = "CREATE TABLE Besitzer " +
              "(Id INT PRIMARY KEY  AUTOINCREMENT," +
              " Name           TEXT NOT NULL)";
        cw.ExecuteQuery(sql);

        sql = "CREATE TABLE Fahrzeug " +
              "(Id INT PRIMARY KEY    AUTOINCREMENT," +
              " Typ           TEXT   NOT NULL," +
              " BesitzerId     INT    NOT NULL," +
              " FOREIGN KEY (BesitzerId) REFERENCES Besitzer(Id)) ON DELETE SET NULL";
        cw.ExecuteQuery(sql);

        // Initial Besitzer table values
        sql = "INSERT INTO Besitzer (Name) " +
                "VALUES ('General Grievous');";
        cw.ExecuteQuery(sql);
        sql = "INSERT INTO Besitzer (Name) " +
                "VALUES ('Listarte');";
        cw.ExecuteQuery(sql);
        sql = "INSERT INTO Besitzer (Name) " +
                "VALUES ('Angela');";
        cw.ExecuteQuery(sql);
        sql = "INSERT INTO Besitzer (Name) " +
                "VALUES ('Greta');";
        cw.ExecuteQuery(sql);

        // Initial Fahrzeug table values
        sql = "INSERT INTO Fahrzeug (Typ, BesitzerId) " +
                "VALUES ('Wheel Bike', 1);";
        cw.ExecuteQuery(sql);
        sql = "INSERT INTO Fahrzeug (Typ, BesitzerId) " +
                "VALUES ('Lista Wings', 2);";
        cw.ExecuteQuery(sql);
        sql = "INSERT INTO Fahrzeug (Typ, BesitzerId) " +
                "VALUES ('Tigerpanzer', 3);";
        cw.ExecuteQuery(sql);
        sql = "INSERT INTO Fahrzeug (Typ, BesitzerId) " +
                "VALUES ('Cybertruck', 4);";
        cw.ExecuteQuery(sql);
    }
}
