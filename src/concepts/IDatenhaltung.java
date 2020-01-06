package concepts;

import models.Besitzer;
import models.Fahrzeug;

public interface IDatenhaltung {
    Besitzer[] getAllBesitzer();

    Fahrzeug[] getAllFahzeuge();

    Besitzer getBesitzer(int besitzerId);

    Fahrzeug getFahrzeug(int fahrzeugId);

    boolean saveBesitzer(Besitzer besitzer);

    boolean saveFahrzeug(Fahrzeug fahrzeug);

    boolean deleteFahrzeug(int fahrzeugId);

    boolean deleteBesitzer(int besitzerId);
}