package concepts;

import models.Besitzer;
import models.Fahrzeug;
import sql.ConnectionWrapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class SqliteDatenhaltung implements IDatenhaltung {
    ConnectionWrapper cw = ConnectionWrapper.GetInstance();
    String sql;

    @FunctionalInterface
    private interface ResultSetMap<T> {
        T apply(ResultSet resultSet) throws SQLException;
    }

    @Override
    public Stream<Besitzer> getAllBesitzer() {
        sql = "SELECT * FROM Besitzer";
        ResultSet rs = null;
        try {
            rs = cw.ExecuteQuery(sql);
        } catch (SQLException e) {
            System.err.printf("Not able to get all Besitzer. Error: %s, StackTrace: %s\n", e.getMessage(), Arrays.toString(e.getStackTrace()));
        }
        return resultSetToStream(rs, (resultSet) -> new Besitzer(resultSet.getInt("Id"), resultSet.getString("Name")));
    }

    private <T> Stream<T> resultSetToStream(ResultSet resultSet, ResultSetMap<T> mapFunction) {
        return StreamSupport.stream(new Spliterators.AbstractSpliterator<>(
                Long.MAX_VALUE, Spliterator.ORDERED) {
            @Override
            public boolean tryAdvance(Consumer<? super T> action) {
                try {
                    if (!resultSet.next()) return false;
                    action.accept(mapFunction.apply(resultSet));
                } catch (SQLException ex) {
                    System.err.printf("Could not get any more data. Error: %s, StackTrace: %s\n", ex.getMessage(), Arrays.toString(ex.getStackTrace()));
                    return false;
                }
                return true;
            }
        }, false);
    }

    @Override
    public Stream<Fahrzeug> getAllFahzeuge() {
        HashMap<Integer, Fahrzeug> fahrzeugList = new HashMap<>();
        sql = "SELECT * FROM Fahrzeug";
        try {
            ResultSet rs = cw.ExecuteQuery(sql);
            while (rs.next()) {
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
        //sql = "INSERT INTO Besitzer Name VALUES ('" + besitzer.getName() + "');";
        //int rows = cw.ExecuteUpdate(sql);
        //if (rows > 0) {
        //    return true;
        //}
        //return false;
        return false;
    }

    @Override
    public boolean saveFahrzeug(Fahrzeug fahrzeug) {
        //sql = "INSERT INTO Fahrzeug Typ VALUES ('" + fahrzeug.getBezeichnung() + "');";
        //int rows = cw.ExecuteUpdate(sql);
        //if (rows > 0) {
        //    return true;
        //}
        return false;
    }

    @Override
    public boolean deleteBesitzer(int besitzerId) {
        //sql = "DELETE FROM Besitzer WHERE ID=" + besitzerId;
        //cw.ExecuteUpdate(sql);
        //int rows = cw.ExecuteUpdate(sql);
        //if (rows > 0) {
        //    return true;
        //}
        return false;
    }

    @Override
    public boolean deleteFahrzeug(int fahrzeugId) {
        //sql = "DELETE FROM Fahrzeug WHERE ID=" + fahrzeugId;
        //int rows = cw.ExecuteUpdate(sql);
        //if (rows > 0) {
        //    return true;
        //}
        return false;
    }
}
