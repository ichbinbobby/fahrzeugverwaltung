package concepts;

import models.Besitzer;
import models.Fahrzeug;

import java.util.stream.Stream;

public interface IDatenhaltung {
    Stream<Besitzer> getAllBesitzer();

    Stream<Fahrzeug> getAllFahzeuge();

    Besitzer getBesitzer(int besitzerId);

    Fahrzeug getFahrzeug(int fahrzeugId);

    boolean saveBesitzer(Besitzer besitzer);

    boolean saveFahrzeug(Fahrzeug fahrzeug);

    boolean deleteFahrzeug(int fahrzeugId);

    boolean deleteBesitzer(int besitzerId);
}