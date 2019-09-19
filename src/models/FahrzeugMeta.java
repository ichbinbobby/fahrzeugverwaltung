package models;

public class FahrzeugMeta {
    private int _fahrzeugId;
    private String _bezeichnung;

    public int getFahrzeugId() {
        return _fahrzeugId;
    }

    public void setFahrzeugId(int fahrzeugId) {
        this._fahrzeugId = fahrzeugId;
    }

    public String getBezeichnung() {
        return _bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this._bezeichnung = bezeichnung;
    }
}
