package concepts;

import models.Besitzer;
import models.BesitzerMeta;
import models.Fahrzeug;
import models.FahrzeugMeta;

import java.util.Comparator;
import java.util.stream.Stream;

public class Fachkonzept1 implements IFachkonzept {
    private IDatenhaltung datenhaltung;

    public Fachkonzept1(IDatenhaltung datenhaltung) {
        this.datenhaltung = datenhaltung;
    }

    @Override
    public Stream<BesitzerMeta> getAllBesitzer() {
        return this.datenhaltung.getAllBesitzer()
                .sorted(Comparator.comparing(BesitzerMeta::getName))
                .map(BesitzerMeta::new);
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
        return this.datenhaltung.getAllFahzeuge()
                .sorted((f1, f2) -> f1.getBezeichnung().compareTo(f2.getBezeichnung()))
                .map(FahrzeugMeta::new);
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
