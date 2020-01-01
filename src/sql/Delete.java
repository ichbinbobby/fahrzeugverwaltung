package sql;

public class Delete {
    public static void main(String args[]) {
        ConnectionWrapper cw = new ConnectionWrapper.GetInstance();
    }
    public void deleteBesitzer (int id){
        String sql = "DELETE FROM Besitzer WHERE ID=" + id;
        cw.ExecuteQuery(sql);
    }
    public void deleteFahrzeug(int id) {
        String sql = "DELETE FROM Fahrzeug WHERE ID=" + id;
        cw.ExecuteQuery(sql);
    }
}
