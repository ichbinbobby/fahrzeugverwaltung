package models;

public class Fahrzeug extends FahrzeugMeta {

    public Fahrzeug(Fahrzeug other) {
        this(other.getFahrzeugId(), other.getBezeichnung());
    }

    public Fahrzeug(int fahrzeugId, String bezeichnung) {
        super(fahrzeugId, bezeichnung);
    }

    public Fahrzeug(String name) {
        this(-1, name);
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