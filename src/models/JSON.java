package models;

import java.util.ArrayList;
import java.util.List;

public class JSON {
    private List<Besitzer> besitzer = new ArrayList();
    private List<Fahrzeug> fahrzeuge = new ArrayList();
    private List<Besitzverhaeltnisse> besitzverhaeltnisse = new ArrayList();

    public JSON(){}

    public List<Besitzer> getBesitzer(){
        return this.besitzer;
    }

    public void setBesitzer(List<Besitzer> besitzer){
        this.besitzer = besitzer;
    }

    public List<Fahrzeug> getFahrzeuge(){
        return this.fahrzeuge;
    }

    public void setFahrzeuge(List<Fahrzeug> fahrzeuge){
        this.fahrzeuge = fahrzeuge;
    }

    public List<Besitzverhaeltnisse> getBesitzverhaeltnisse(){
        return this.besitzverhaeltnisse;
    }

    public void setBesitzverhaeltnisse(List<Besitzverhaeltnisse> besitzverhaeltnisse){
        this.besitzverhaeltnisse = besitzverhaeltnisse;
    }
}