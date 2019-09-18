package concepts;

import models.Besitzer;
import models.BesitzerMeta;
import models.Fahrzeug;
import models.FahrzeugMeta;

public interface IFachkonzept {
    BesitzerMeta[] getAllBesitzer();

    Besitzer getBesitzerDetails(int besitzerId);

    Besitzer getBeistzerByFahrzeug(int fahrzeugId);

    boolean saveBesitzer(Besitzer besitzer);

    boolean deleteBesitzer(int besitzerId);

    FahrzeugMeta[] getAllFahrzeuge();

    Fahrzeug getFahrzeugDetails(int fahrzeugId);

    FahrzeugMeta[] getFahrzeugeByBesitzer(int besitzerId);

    boolean saveFahrzeug(Fahrzeug fahrzeug);

    boolean deleteFahrzeug(int fahrzeugId);

    boolean setNewBesitzer(int fahrzeugId, int besitzerId);
}