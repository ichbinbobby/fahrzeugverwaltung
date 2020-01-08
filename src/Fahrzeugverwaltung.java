import components.datenhaltung.Datenhaltung2;
import components.tui.Tui;
import concepts.Fachkonzept1;
import concepts.Fachkonzept2;

public class Fahrzeugverwaltung {
    public static void main(String[] args) {
        new Tui(new Fachkonzept1(new Datenhaltung2()));
    }
}
