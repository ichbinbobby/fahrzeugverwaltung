package concepts;

import models.Besitzer;
import models.Fahrzeug;
import models.FahrzeugMeta;

import java.util.stream.Stream;

public interface IDatenhaltung {
    Stream<Besitzer> getAllBesitzer();

    Stream<Fahrzeug> getAllFahzeuge();

    Stream<FahrzeugMeta> getFahrzeugeByBesitzer(int besitzerId);

    Besitzer getBesitzerByFahrzeug(int fahrzeugId);

    Besitzer getBesitzer(int besitzerId);

    Fahrzeug getFahrzeug(int fahrzeugId);

    int saveBesitzer(Besitzer besitzer);

    int saveFahrzeug(Fahrzeug fahrzeug);

    boolean deleteBesitzer(int besitzerId);

    boolean deleteFahrzeug(int fahrzeugId);

    boolean setNewBesitzer(int fahrzeugId, int besitzerId);
}