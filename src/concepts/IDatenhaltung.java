package concepts;

import models.Besitzer;
import models.Fahrzeug;

public interface IDatenhaltung {
    Besitzer[] GetAllBeistzer();

    Fahrzeug[] GetAllFahzeuge();

    Besitzer GetBeistzer(int beistzerId);

    Fahrzeug GetFahrzeug(int fahrzeugId);

    boolean SaveBesitzer(Besitzer besitzer);

    boolean SaveFahrzeug(Fahrzeug fahrzeug);

}