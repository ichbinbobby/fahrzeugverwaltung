package concepts;

import models.Besitzer;
import models.Fahrzeug;
import models.FahrzeugMeta;
import sql.ConnectionWrapper;
import sql.SqlParameter;
import sql.SqlParameterType;
import sql.UpdateResult;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class SqliteDatenhaltung implements IDatenhaltung {
    ConnectionWrapper cw = ConnectionWrapper.GetInstance();

    public SqliteDatenhaltung() {
        createBesitzerTable();
        createFahrzeugTable();
    }

    void insertNewBesitzer(String name) throws SQLException {
        String sql = "INSERT INTO Besitzer (Name) VALUES (?);";
        cw.Execute(sql, new SqlParameter(1, name));
    }

    void insertNewFahrzeug(String bezeichnung, int besitzerId) throws SQLException {
        String sql = "INSERT INTO Fahrzeug (Bezeichnung, BesitzerId) VALUES (?, ?);";
        cw.Execute(sql, new SqlParameter(1, bezeichnung), new SqlParameter(2, besitzerId, SqlParameterType.INT));
    }

    private void createDummyValues() throws SQLException {
        // Initial Besitzer table values
        insertNewBesitzer("General Grievous");
        insertNewBesitzer("Listarte");
        insertNewBesitzer("Angela");
        insertNewBesitzer("Greta");

        // Initial Fahrzeug table values
        insertNewFahrzeug("Wheel Bike", 1);
        insertNewFahrzeug("Lista Wings", 2);
        insertNewFahrzeug("Tigerpanzer", 3);
        insertNewFahrzeug("Cybertruck", 4);
    }

    private void createFahrzeugTable() {
        String sql = "CREATE TABLE IF NOT EXISTS Fahrzeug (Id INTEGER PRIMARY KEY AUTOINCREMENT, Bezeichnung TEXT NOT NULL, BesitzerId INT, FOREIGN KEY (BesitzerId) REFERENCES Besitzer(Id) ON DELETE SET NULL)";
        try {
            cw.Execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createBesitzerTable() {
        String sql = "CREATE TABLE IF NOT EXISTS Besitzer (Id INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT NOT NULL)";
        try {
            cw.Execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FunctionalInterface
    private interface ResultSetMap<T> {
        T apply(ResultSet resultSet) throws SQLException;
    }

    @Override
    public Stream<Besitzer> getAllBesitzer() {
        String sql = "SELECT * FROM Besitzer";
        try {
            ResultSet rs = cw.ExecuteQuery(sql);
            return resultSetToStream(rs, (resultSet) -> new Besitzer(resultSet.getInt("Id"), resultSet.getString("Name")));
        } catch (SQLException ex) {
            System.err.printf("Unable to get all Besitzer. Error: %s, StackTrace: %s\n", ex.getMessage(), Arrays.toString(ex.getStackTrace()));
        }
        return null;
    }

    private <T> Stream<T> resultSetToStream(ResultSet resultSet, ResultSetMap<T> mapFunction) {
        if (resultSet == null) {
            return null;
        }

        return StreamSupport.stream(new Spliterators.AbstractSpliterator<>(
                Long.MAX_VALUE, Spliterator.ORDERED) {
            @Override
            public boolean tryAdvance(Consumer<? super T> action) {
                try {
                    if (!resultSet.next()) {
                        resultSet.close();
                        return false;
                    }
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
        String sql = "SELECT * FROM Fahrzeug";
        try {
            ResultSet rs = cw.ExecuteQuery(sql);
            return resultSetToStream(rs, (resultSet) -> new Fahrzeug(resultSet.getInt("Id"), resultSet.getString("Bezeichnung")));
        } catch (SQLException ex) {
            System.err.printf("Unable to get all Fahrzeuge. Error: %s, StackTrace: %s\n", ex.getMessage(), Arrays.toString(ex.getStackTrace()));
        }
        return null;
    }

    @Override
    public Stream<FahrzeugMeta> getFahrzeugeByBesitzer(int besitzerId) {
        String sql = "SELECT * FROM Fahrzeug WHERE BesitzerId = ?";
        try {
            ResultSet rs = cw.ExecuteQuery(sql, new SqlParameter(1, besitzerId, SqlParameterType.INT));
            return resultSetToStream(rs, resultSet -> new FahrzeugMeta(resultSet.getInt("Id"), resultSet.getString("Bezeichnung")));
        } catch (SQLException ex) {
            System.err.printf("Unable to get Fahrzeuge by Besitzer. Error: %s, StackTrace: %s\n", ex.getMessage(), Arrays.toString(ex.getStackTrace()));
        }
        return null;
    }

    @Override
    public Besitzer getBesitzerByFahrzeug(int fahrzeugId) {
        String sql = "SELECT BesitzerId FROM Fahrzeug WHERE Id = ?";
        try (ResultSet rs = cw.ExecuteQuery(sql, new SqlParameter(1, fahrzeugId, SqlParameterType.INT))) {
            if (rs.next()) {
                return getBesitzer(rs.getInt("BesitzerId"));
            }
        } catch (SQLException ex) {
            System.err.printf("Unable to get Fahrzeuge by Besitzer. Error: %s, StackTrace: %s\n", ex.getMessage(), Arrays.toString(ex.getStackTrace()));
        }
        return null;
    }

    @Override
    public Besitzer getBesitzer(int besitzerId) {
        String sql = "SELECT * FROM Besitzer WHERE Id = ?";
        try (ResultSet rs = cw.ExecuteQuery(sql, new SqlParameter(1, besitzerId, SqlParameterType.INT))) {
            return new Besitzer(rs.getInt("Id"), rs.getString("Name"));
        } catch (SQLException ex) {
            System.err.printf("Unable to get Besitzer by Id (%d). Error: %s, StackTrace: %s\n", besitzerId, ex.getMessage(), Arrays.toString(ex.getStackTrace()));
        }
        return null;
    }

    @Override
    public Fahrzeug getFahrzeug(int fahrzeugId) {
        String sql = "SELECT * FROM Fahrzeug WHERE Id = ?";
        try {
            ResultSet rs = cw.ExecuteQuery(sql, new SqlParameter(1, fahrzeugId, SqlParameterType.INT));
            return new Fahrzeug(rs.getInt("Id"), rs.getString("Bezeichnung"));
        } catch (SQLException ex) {
            System.err.printf("Unable to get Fahrzeug by Id (%d). Error: %s, StackTrace: %s\n", fahrzeugId, ex.getMessage(), Arrays.toString(ex.getStackTrace()));
        }
        return null;
    }

    private int insertBesitzer(Besitzer besitzer) throws SQLException {
        String sql = "INSERT INTO Besitzer (Name) VALUES (?);";
        UpdateResult result = cw.ExecuteUpdate(sql,
                new SqlParameter(1, besitzer.getName())
        );
        if (result.updatedRows == 0 || !result.generatedKeys.next()) {
            return -1;
        }

        int besitzerId = result.generatedKeys.getInt(1);
        result.generatedKeys.close();
        return besitzerId;
    }

    private int updateBesitzer(Besitzer besitzer) throws SQLException {
        String sql = "UPDATE Besitzer SET Name = ? WHERE Id = ?";
        UpdateResult result = cw.ExecuteUpdate(sql,
                new SqlParameter(1, besitzer.getName()),
                new SqlParameter(2, besitzer.getBesitzerId(), SqlParameterType.INT)
        );
        if (result.generatedKeys != null && !result.generatedKeys.isClosed()) {
            result.generatedKeys.close();
        }

        if (result.updatedRows == 0) {
            return -1;
        }

        return besitzer.getBesitzerId();
    }

    private int insertFahrzeug(Fahrzeug fahrzeug) throws SQLException {
        String sql = "INSERT INTO Fahrzeug (Bezeichnung) VALUES (?);";
        UpdateResult result = cw.ExecuteUpdate(sql,
                new SqlParameter(1, fahrzeug.getBezeichnung())
        );
        if (result.updatedRows == 0 || !result.generatedKeys.next()) {
            return -1;
        }

        int fahrzeugId = result.generatedKeys.getInt(1);
        result.generatedKeys.close();

        return fahrzeugId;
    }

    private int updateFahrzeug(Fahrzeug fahrzeug) throws SQLException {
        String sql = "UPDATE Fahrzeug SET Bezeichnung = ? WHERE Id = ?";
        UpdateResult result = cw.ExecuteUpdate(sql,
                new SqlParameter(1, fahrzeug.getBezeichnung()),
                new SqlParameter(2, fahrzeug.getFahrzeugId(), SqlParameterType.INT)
        );

        if (result.generatedKeys != null && !result.generatedKeys.isClosed()) {
            result.generatedKeys.close();
        }

        if (result.updatedRows == 0) {
            return -1;
        }

        return fahrzeug.getFahrzeugId();
    }

    @Override
    public int saveBesitzer(Besitzer besitzer) {
        try {
            if (besitzer.getBesitzerId() == -1) {
                return insertBesitzer(besitzer);
            } else {
                return updateBesitzer(besitzer);
            }
        } catch (SQLException ex) {
            System.err.printf("Unable to save Besitzer. Error: %s, StackTrace: %s\n", ex.getMessage(), Arrays.toString(ex.getStackTrace()));
        }
        return -1;
    }

    @Override
    public int saveFahrzeug(Fahrzeug fahrzeug) {
        try {
            if (fahrzeug.getFahrzeugId() == -1) {
                return insertFahrzeug(fahrzeug);
            } else {
                return updateFahrzeug(fahrzeug);
            }
        } catch (SQLException ex) {
            System.err.printf("Unable to save Fahrzeug. Error: %s, StackTrace: %s\n", ex.getMessage(), Arrays.toString(ex.getStackTrace()));
        }
        return -1;
    }

    @Override
    public boolean deleteBesitzer(int besitzerId) {
        String sql = "DELETE FROM Besitzer WHERE Id = ?";
        try {
            UpdateResult result = cw.ExecuteUpdate(sql,
                    new SqlParameter(1, besitzerId, SqlParameterType.INT)
            );

            if (result.generatedKeys != null && !result.generatedKeys.isClosed()) {
                result.generatedKeys.close();
            }

            return result.updatedRows != 0;
        } catch (SQLException ex) {
            System.err.printf("Unable to delete Besitzer. Error: %s, StackTrace: %s\n", ex.getMessage(), Arrays.toString(ex.getStackTrace()));
        }
        return false;
    }

    @Override
    public boolean deleteFahrzeug(int fahrzeugId) {
        String sql = "DELETE FROM Fahrzeug WHERE Id = ?";
        try {
            UpdateResult result = cw.ExecuteUpdate(sql,
                    new SqlParameter(1, fahrzeugId, SqlParameterType.INT)
            );

            if (result.generatedKeys != null && !result.generatedKeys.isClosed()) {
                result.generatedKeys.close();
            }

            return result.updatedRows != 0;
        } catch (SQLException ex) {
            System.err.printf("Unable to delete Fahrzeug. Error: %s, StackTrace: %s\n", ex.getMessage(), Arrays.toString(ex.getStackTrace()));
        }
        return false;
    }

    @Override
    public boolean setNewBesitzer(int fahrzeugId, int besitzerId) {
        String sql = "UPDATE Fahrzeug SET BesitzerId = ? WHERE Id = ?";
        try {
            UpdateResult result = cw.ExecuteUpdate(sql,
                    new SqlParameter(1, besitzerId, besitzerId == -1 ? SqlParameterType.NULL : SqlParameterType.INT),
                    new SqlParameter(2, fahrzeugId, SqlParameterType.INT)
            );
            if (result.generatedKeys != null && !result.generatedKeys.isClosed()) {
                result.generatedKeys.close();
            }

            return result.updatedRows != 0;
        } catch (SQLException ex) {
            System.err.printf("Unable to set new Besitzer for Fahrzeug. Error: %s, StackTrace: %s", ex.getMessage(), Arrays.toString(ex.getStackTrace()));
        }
        return false;
    }
}
