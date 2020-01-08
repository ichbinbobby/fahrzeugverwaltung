package concepts;

import models.Besitzer;
import models.BesitzerMeta;
import models.Fahrzeug;
import models.FahrzeugMeta;

import java.util.stream.Stream;

public interface IDatenhaltung {
    Stream<BesitzerMeta> getAllBesitzer();

    Stream<FahrzeugMeta> getAllFahzeuge();

    Stream<FahrzeugMeta> getFahrzeugeByBesitzer(int besitzerId);

    Besitzer getBesitzerByFahrzeug(int fahrzeugId);

    Besitzer getBesitzerDetails(int besitzerId);

    Fahrzeug getFahrzeugDetails(int fahrzeugId);

    int saveBesitzer(Besitzer besitzer);

    int saveFahrzeug(Fahrzeug fahrzeug);

    boolean deleteBesitzer(int besitzerId);

    boolean deleteFahrzeug(int fahrzeugId);

    boolean setNewBesitzer(int fahrzeugId, int besitzerId);
}