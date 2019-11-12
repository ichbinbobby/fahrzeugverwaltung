package models;

public class Fahrzeug extends FahrzeugMeta {
    public Fahrzeug(int fahrzeugId, String bezeichnung) {
        super(fahrzeugId, bezeichnung);
    }

    @Override
    public Fahrzeug setFahrzeugId(int fahrzeugId) {
        return (Fahrzeug) super.setFahrzeugId(fahrzeugId);
    }

    @Override
    public Fahrzeug setBezeichnung(String bezeichnung) {
        return (Fahrzeug) super.setBezeichnung(bezeichnung);
    }


}