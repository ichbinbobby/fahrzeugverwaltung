package models;

public class FahrzeugMeta {
    private int _fahrzeugId;
    private String _bezeichnung;

    public FahrzeugMeta(int fahrzeugId, String bezeichnung) {
        this._fahrzeugId = fahrzeugId;
        this._bezeichnung = bezeichnung;
    }

    public int getFahrzeugId() {
        return _fahrzeugId;
    }

    public FahrzeugMeta setFahrzeugId(int fahrzeugId) {
        this._fahrzeugId = fahrzeugId;
        return this;
    }

    public String getBezeichnung() {
        return _bezeichnung;
    }

    public FahrzeugMeta setBezeichnung(String bezeichnung) {
        this._bezeichnung = bezeichnung;
        return this;
    }
}
