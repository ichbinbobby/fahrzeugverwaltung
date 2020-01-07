import components.tui.Tui;
import concepts.Fachkonzept1;
import concepts.SqliteDatenhaltung;

public class Fahrzeugverwaltung {
    public static void main(String[] args) {
        new Tui(new Fachkonzept1(new SqliteDatenhaltung()));
    }
}
