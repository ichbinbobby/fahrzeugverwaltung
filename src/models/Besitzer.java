package models;

public class Besitzer extends BesitzerMeta {
    public Besitzer(int id, String name) {
        super(id, name);
    }

    @Override
    public Besitzer setBesitzerId(int besitzerId) {
        return (Besitzer) super.setBesitzerId(besitzerId);
    }

    @Override
    public Besitzer setName(String name) {
        return (Besitzer) super.setName(name);
    }
}