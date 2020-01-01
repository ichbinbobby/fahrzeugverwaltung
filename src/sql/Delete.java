package sql;

public class Delete {
    ConnectionWrapper cw = ConnectionWrapper.GetInstance();
    public void deleteBesitzer (int id){
        String sql = "DELETE FROM Besitzer WHERE ID=" + id;
        cw.ExecuteQuery(sql);
    }
    public void deleteFahrzeug(int id) {
        String sql = "DELETE FROM Fahrzeug WHERE ID=" + id;
        cw.ExecuteQuery(sql);
    }
}
