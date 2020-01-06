package concepts;

import models.Besitzer;
import models.BesitzerMeta;
import models.Fahrzeug;
import sql.ConnectionWrapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.stream.Stream;

public class SqliteDatenhaltung implements IDatenhaltung{
    ConnectionWrapper cw = ConnectionWrapper.GetInstance();
    String sql;

    @Override
    public Stream<Besitzer> getAllBesitzer() {
        HashMap<Integer, Besitzer> besitzerList = new HashMap<>();
        sql = "SELECT * FROM Besitzer";
        try {
            ResultSet rs = cw.ExecuteQuery(sql);
            while(rs.next()) {
                besitzerList.put(rs.getInt("Id"), new Besitzer(rs.getInt("Id"), rs.getString("Name")));
            }
        } catch (SQLException e ) {
            System.err.println(e);
        }
        return besitzerList.values().stream();
    }

    @Override
    public Stream<Fahrzeug> getAllFahzeuge() {
        HashMap<Integer, Fahrzeug> fahrzeugList = new HashMap<>();
        sql = "SELECT * FROM Fahrzeug";
        try {
            ResultSet rs = cw.ExecuteQuery(sql);
            while(rs.next()) {
                // BesitzerId mitgeben?
                fahrzeugList.put(rs.getInt("Id"), new Fahrzeug(rs.getInt("Id"), rs.getString("Typ")));
            }
        } catch (SQLException e ) {
            System.err.println(e);
        }
        return fahrzeugList.values().stream();
    }

    @Override
    public Besitzer getBesitzer(int besitzerId) {
        Besitzer besitzer = new Besitzer(0, null);
        sql = "SELECT * FROM Besitzer WHERE Id=" + besitzerId;
        try {
            ResultSet rs = cw.ExecuteQuery(sql);
            //besitzer = new Besitzer(rs.getInt("Id"), rs.getString("Name"));
            besitzer.setBesitzerId(rs.getInt("Id"));
            besitzer.setName(rs.getString("Name"));
        } catch (SQLException e ) {
            System.err.println(e);
        }
        return besitzer;
    }

    @Override
    public Fahrzeug getFahrzeug(int fahrzeugId) {
        Fahrzeug fahrzeug = new Fahrzeug(0, null);
        sql = "SELECT * FROM Fahrzeug WHERE Id=" + fahrzeugId;
        try {
            ResultSet rs = cw.ExecuteQuery(sql);
            fahrzeug.setFahrzeugId(rs.getInt("Id"));
            fahrzeug.setBezeichnung(rs.getString("Typ"));
        } catch (SQLException e ) {
            System.err.println(e);
        }
        return fahrzeug;
    }

    // what if fahrzeug already exists?
    @Override
    public boolean saveBesitzer(Besitzer besitzer) {
        //id? oder autoincrement
        sql = "INSERT INTO Besitzer Name VALUES ('" + besitzer.getName() + "');";
        int rows = cw.ExecuteUpdate(sql);
        if (rows > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean saveFahrzeug(Fahrzeug fahrzeug) {
        sql = "INSERT INTO Fahrzeug Typ VALUES ('" + fahrzeug.getBezeichnung() + "');";
        int rows = cw.ExecuteUpdate(sql);
        if (rows > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteBesitzer(int besitzerId) {
        sql = "DELETE FROM Besitzer WHERE ID=" + besitzerId;
        cw.ExecuteUpdate(sql);
        int rows = cw.ExecuteUpdate(sql);
        if (rows > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteFahrzeug(int fahrzeugId) {
        sql = "DELETE FROM Fahrzeug WHERE ID=" + fahrzeugId;
        int rows = cw.ExecuteUpdate(sql);
        if (rows > 0) {
            return true;
        }
        return false;
    }
}
