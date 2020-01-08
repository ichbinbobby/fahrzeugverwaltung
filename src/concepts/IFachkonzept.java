package concepts;

import models.Besitzer;
import models.BesitzerMeta;
import models.Fahrzeug;
import models.FahrzeugMeta;

import java.util.stream.Stream;

public interface IFachkonzept {
    Stream<BesitzerMeta> getAllBesitzer();

    Besitzer getBesitzerDetails(int besitzerId);

    Besitzer getBesitzerByFahrzeug(int fahrzeugId);

    int saveBesitzer(Besitzer besitzer);

    boolean deleteBesitzer(int besitzerId);

    Stream<FahrzeugMeta> getAllFahrzeuge();

    Fahrzeug getFahrzeugDetails(int fahrzeugId);

    Stream<FahrzeugMeta> getFahrzeugeByBesitzer(int besitzerId);

    int saveFahrzeug(Fahrzeug fahrzeug);

    boolean deleteFahrzeug(int fahrzeugId);

    boolean setNewBesitzer(int fahrzeugId, int besitzerId);
}