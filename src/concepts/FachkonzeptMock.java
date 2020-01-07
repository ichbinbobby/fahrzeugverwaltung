package concepts;

import models.Besitzer;
import models.BesitzerMeta;
import models.Fahrzeug;
import models.FahrzeugMeta;

import java.util.*;
import java.util.stream.Stream;

public class FachkonzeptMock implements IFachkonzept {
    private HashMap<Integer, Besitzer> besitzerList = new HashMap<>();
    private HashMap<Integer, Fahrzeug> fahrzeugList = new HashMap<>();
    private Map<Integer, Set<Integer>> besitzerFahrzeugeMapping = new HashMap<>();
    private Map<Integer, Integer> fahrzeugeBesitzerMapping = new HashMap<>();

    private int maxBesitzerId = 1;
    private int maxFahrzeugId = 1;

    public FachkonzeptMock() {
        besitzerList.put(1, new Besitzer(1, "Greta"));
        besitzerList.put(2, new Besitzer(2, "Max"));
        besitzerList.put(3, new Besitzer(3, "Franz"));
        besitzerList.put(4, new Besitzer(4, "Wilfred"));
        besitzerList.put(5, new Besitzer(5, "Listate"));
        besitzerList.put(6, new Besitzer(6, "Jana"));
        besitzerList.put(7, new Besitzer(7, "Thomas"));

        fahrzeugList.put(1, new Fahrzeug(1, "Cybertruck"));
        fahrzeugList.put(2, new Fahrzeug(2, "Tesla"));
        fahrzeugList.put(3, new Fahrzeug(3, "VW Gold"));
        fahrzeugList.put(4, new Fahrzeug(4, "Smart"));
        fahrzeugList.put(5, new Fahrzeug(5, "BMW"));

        besitzerFahrzeugeMapping.put(1, new HashSet<>(Arrays.asList(1, 3, 4)));
        besitzerFahrzeugeMapping.put(2, new HashSet<>(Arrays.asList(2, 5)));

        besitzerFahrzeugeMapping.forEach((besitzerId, fahrzeugSet) -> fahrzeugSet.forEach(fahrzeugId -> fahrzeugeBesitzerMapping.put(fahrzeugId, besitzerId)));

        maxFahrzeugId = fahrzeugList.keySet().stream().max(Comparator.comparingInt(a -> a)).orElse(maxFahrzeugId);
        maxBesitzerId = besitzerList.keySet().stream().max(Comparator.comparingInt(a -> a)).orElse(maxBesitzerId);
    }

    @Override
    public Stream<BesitzerMeta> getAllBesitzer() {
        return besitzerList.values().stream().map(BesitzerMeta::new);
    }

    @Override
    public Besitzer getBesitzerDetails(int besitzerId) {
        return besitzerList.getOrDefault(besitzerId, null);
    }

    @Override
    public Besitzer getBeistzerByFahrzeug(int fahrzeugId) {
        return besitzerList.getOrDefault(fahrzeugeBesitzerMapping.getOrDefault(fahrzeugId, -1), null);
    }

    @Override
    public int saveBesitzer(Besitzer besitzer) {
        besitzer = new Besitzer(besitzer);
        if (besitzer.getBesitzerId() == -1) {
            besitzer.setBesitzerId(maxBesitzerId + 1);
        }
        if (besitzer.getBesitzerId() > maxBesitzerId) {
            maxBesitzerId = besitzer.getBesitzerId();
        }
        besitzerList.put(besitzer.getBesitzerId(), besitzer);
        return besitzer.getBesitzerId();
    }

    @Override
    public boolean deleteBesitzer(int besitzerId) {
        this.removeMappingsByBesitzer(besitzerId);
        return besitzerList.remove(besitzerId) != null;
    }

    private void removeMappingsByBesitzer(int besitzerId) {
        besitzerFahrzeugeMapping.getOrDefault(besitzerId, new HashSet<>())
                .forEach(fahrzeugId -> fahrzeugeBesitzerMapping.remove(fahrzeugId));
        besitzerFahrzeugeMapping.remove(besitzerId);
    }

    private void removeMappingsByFahrzeug(int fahrzeugId) {
        int besitzerId = fahrzeugeBesitzerMapping.getOrDefault(fahrzeugId, -1);
        besitzerFahrzeugeMapping.getOrDefault(besitzerId, new HashSet<>()).remove(fahrzeugId);
        fahrzeugeBesitzerMapping.remove(fahrzeugId);
    }

    @Override
    public Stream<FahrzeugMeta> getAllFahrzeuge() {
        return fahrzeugList
                .values()
                .stream()
                .map(FahrzeugMeta::new);
    }

    @Override
    public Fahrzeug getFahrzeugDetails(int fahrzeugId) {
        return fahrzeugList.getOrDefault(fahrzeugId, null);
    }

    @Override
    public Stream<FahrzeugMeta> getFahrzeugeByBesitzer(int besitzerId) {
        return besitzerFahrzeugeMapping
                .getOrDefault(besitzerId, new HashSet<>())
                .stream()
                .map(this::getFahrzeugDetails)
                .map(FahrzeugMeta::new);
    }

    @Override
    public int saveFahrzeug(Fahrzeug fahrzeug) {
        fahrzeug = new Fahrzeug(fahrzeug);
        if (fahrzeug.getFahrzeugId() == -1) {
            fahrzeug.setFahrzeugId(maxFahrzeugId + 1);
        }
        if (fahrzeug.getFahrzeugId() > maxFahrzeugId) {
            maxFahrzeugId = fahrzeug.getFahrzeugId();
        }
        fahrzeugList.put(fahrzeug.getFahrzeugId(), fahrzeug);
        return fahrzeug.getFahrzeugId();
    }

    @Override
    public boolean deleteFahrzeug(int fahrzeugId) {
        removeMappingsByFahrzeug(fahrzeugId);
        return fahrzeugList.remove(fahrzeugId) != null;
    }

    @Override
    public boolean setNewBesitzer(int fahrzeugId, int besitzerId) {
        if (fahrzeugId == -1) {
            return false;
        }
        removeMappingsByFahrzeug(fahrzeugId);
        if (besitzerId == -1) {
            return true;
        }

        fahrzeugeBesitzerMapping.put(fahrzeugId, besitzerId);
        Set<Integer> fahrzeuge = besitzerFahrzeugeMapping.getOrDefault(besitzerId, null);
        if (fahrzeuge == null) {
            fahrzeuge = new HashSet<>();
            besitzerFahrzeugeMapping.put(besitzerId, fahrzeuge);
        }
        fahrzeuge.add(fahrzeugId);
        return true;
    }
}
