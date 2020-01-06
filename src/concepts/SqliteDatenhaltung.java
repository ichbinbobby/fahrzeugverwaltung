package concepts;

import models.Besitzer;
import models.Fahrzeug;
import sql.ConnectionWrapper;

import java.util.stream.Stream;

public class SqliteDatenhaltung implements IDatenhaltung{
    ConnectionWrapper cw = ConnectionWrapper.GetInstance();
    String sql;

    @Override
    public Stream<Besitzer> getAllBesitzer() {
        sql = "SELECT Name FROM Besitzer";
        var t = cw.ExecuteQuery(sql);
        return null;
    }

    @Override
    public Stream<Fahrzeug> getAllFahzeuge() {
        return null;
    }

    @Override
    public Besitzer getBesitzer(int besitzerId) {
        return null;
    }

    @Override
    public Fahrzeug getFahrzeug(int fahrzeugId) {
        return null;
    }

    @Override
    public boolean saveBesitzer(Besitzer besitzer) {
        return false;
    }

    @Override
    public boolean saveFahrzeug(Fahrzeug fahrzeug) {
        return false;
    }

    @Override
    public boolean deleteFahrzeug(int fahrzeugId) {
        return false;
    }

    @Override
    public boolean deleteBesitzer(int besitzerId) {
        return false;
    }
}
