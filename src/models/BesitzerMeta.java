package models;

import org.jetbrains.annotations.NotNull;

public class BesitzerMeta implements Comparable<BesitzerMeta> {

    public BesitzerMeta(String name) {
        this(-1, name);
    }

    public BesitzerMeta(int id, String name) {
        setBesitzerId(id);
        setName(name);
    }

    public BesitzerMeta(BesitzerMeta other) {
        this(other.getBesitzerId(), other.getName());
    }

    private int _besitzerId;
    private String _name;

    public int getBesitzerId() {
        return _besitzerId;
    }

    public BesitzerMeta setBesitzerId(int besitzerId) {
        this._besitzerId = besitzerId;
        return this;
    }

    public String getName() {
        return _name;
    }

    public BesitzerMeta setName(String name) {
        this._name = name;
        return this;
    }

    @Override
    public int compareTo(@NotNull BesitzerMeta o) {
        return getName().compareToIgnoreCase(o.getName());
    }
}
