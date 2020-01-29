package concepts;

import models.Besitzer;
import models.BesitzerMeta;
import models.Fahrzeug;
import models.FahrzeugMeta;
import sortingBehaviour.ISortingBehaviour;
import sortingBehaviour.NaturalSorting;

import java.util.stream.Stream;

public class Fachkonzept implements IFachkonzept {
    private IDatenhaltung datenhaltung;
    private ISortingBehaviour sortingBehaviour;

    public Fachkonzept(IDatenhaltung datenhaltung) {
        this.datenhaltung = datenhaltung;
        this.sortingBehaviour = new NaturalSorting();
    }

    public Fachkonzept(IDatenhaltung datenhaltung, ISortingBehaviour sortingBehaviour) {
        this.datenhaltung = datenhaltung;
        this.sortingBehaviour = sortingBehaviour;
    }

    @Override
    public Stream<BesitzerMeta> getAllBesitzer() {
        return this.sortingBehaviour.sort(this.datenhaltung.getAllBesitzer());
    }

    @Override
    public Besitzer getBesitzerDetails(int besitzerId) {
        return this.datenhaltung.getBesitzerDetails(besitzerId);
    }

    @Override
    public Besitzer getBesitzerByFahrzeug(int fahrzeugId) {
        return this.datenhaltung.getBesitzerByFahrzeug(fahrzeugId);
    }

    @Override
    public int saveBesitzer(Besitzer besitzer) {
        return this.datenhaltung.saveBesitzer(besitzer);
    }

    @Override
    public boolean deleteBesitzer(int besitzerId) {
        return this.datenhaltung.deleteBesitzer(besitzerId);
    }

    @Override
    public Stream<FahrzeugMeta> getAllFahrzeuge() {
        return this.sortingBehaviour.sort(this.datenhaltung.getAllFahzeuge(), (f1, f2) -> f1.getBezeichnung().compareToIgnoreCase(f2.getBezeichnung()));
    }

    @Override
    public Fahrzeug getFahrzeugDetails(int fahrzeugId) {
        return this.datenhaltung.getFahrzeugDetails(fahrzeugId);
    }

    @Override
    public Stream<FahrzeugMeta> getFahrzeugeByBesitzer(int besitzerId) {
        return this.datenhaltung.getFahrzeugeByBesitzer(besitzerId);
    }

    @Override
    public int saveFahrzeug(Fahrzeug fahrzeug) {
        return this.datenhaltung.saveFahrzeug(fahrzeug);
    }

    @Override
    public boolean deleteFahrzeug(int fahrzeugId) {
        return this.datenhaltung.deleteFahrzeug(fahrzeugId);
    }

    @Override
    public boolean setNewBesitzer(int fahrzeugId, int besitzerId) {
        return this.datenhaltung.setNewBesitzer(fahrzeugId, besitzerId);
    }
}
