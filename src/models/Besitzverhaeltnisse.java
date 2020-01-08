package models;

import java.util.Set;

public class Besitzverhaeltnisse {
    private int besitzerId;
    private Set<Integer> fahrzeuge;

    public Besitzverhaeltnisse(){
        besitzerId = 1;
        fahrzeuge.add(1);
        fahrzeuge.add(2);
        fahrzeuge.add(3);
    }

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
