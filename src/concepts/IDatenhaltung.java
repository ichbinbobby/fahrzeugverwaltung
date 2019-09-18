package concepts;

import models.Besitzer;
import models.Fahrzeug;

public interface IDatenhaltung {
    Besitzer[] getAllBeistzer();

    Fahrzeug[] getAllFahzeuge();

    Besitzer getBeistzer(int beistzerId);

    Fahrzeug getFahrzeug(int fahrzeugId);

    boolean saveBesitzer(Besitzer besitzer);

    boolean saveFahrzeug(Fahrzeug fahrzeug);

    boolean deleteFahrzeug(int fahrzeugId);

    boolean deleteBesitzer(int besitzerId);
}