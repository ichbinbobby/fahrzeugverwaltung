package models;

import java.util.Set;

public class Besitzverhaeltnisse {
    public Besitzverhaeltnisse(){}

    private int besitzerId;

    private Set<Integer> fahrzeuge;

    public int getBesitzerId(){
        return this.besitzerId;
    }

    public void setBesitzerId(int besitzerId){
        this.besitzerId = besitzerId;
    }

    public Set<Integer> getFahrzeuge(){
        return this.fahrzeuge;
    }

    public void setFahrzeuge(Set<Integer> fahrzeuge){
        this.fahrzeuge = fahrzeuge;
    }
}
