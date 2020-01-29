import components.datenhaltung.DatenhaltungFactory;
import components.datenhaltung.DatenhaltungsTyp;
import components.gui.GraphicalUserInterface;
import concepts.Fachkonzept;
import sortingBehaviour.NaturalSorting;

public class Fahrzeugverwaltung {
    public static void main(String[] args) {
        var datenhaltung = DatenhaltungFactory.createDatenhaltung(DatenhaltungsTyp.Json);
        new GraphicalUserInterface(new Fachkonzept(datenhaltung, new NaturalSorting()));
    }
}
