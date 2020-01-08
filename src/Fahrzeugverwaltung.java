import components.datenhaltung.datenhaltung2;
import components.tui.Tui;
import concepts.Fachkonzept1;

public class Fahrzeugverwaltung {
    public static void main(String[] args) {
        new Tui(new Fachkonzept1(new datenhaltung2()));
    }
}
