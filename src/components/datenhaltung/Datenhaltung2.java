package components.datenhaltung;

import com.google.gson.Gson;
import concepts.IDatenhaltung;
import models.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Set;

public class Datenhaltung2 implements IDatenhaltung {
    private Gson gson;
    private Path file = Paths.get("Fahrzeugverwaltung.json");

    public Datenhaltung2(){
        this.gson = new Gson();
        createFileIfNotExist();
    }

    private void createFileIfNotExist(){
        try{
            readJSON();
        } catch (IOException e){
            JSON json = new JSON();
            try {
                writeJSON(json);
            } catch (IOException e2){
                System.exit(-1);
            }
        }
    }

    private JSON readJSON() throws IOException {
        String jsonString = Files.readString(this.file);
        return gson.fromJson(jsonString, JSON.class);
    }

    private void writeJSON(JSON json) throws IOException {
        String jsonString = gson.toJson(json);
        Files.writeString(this.file, jsonString);
    }

    @Override
    public Stream<BesitzerMeta> getAllBesitzer() {
        try {
            return readJSON().getBesitzer().stream().map(BesitzerMeta::new);
        } catch(IOException e) {
            return null;
        }
    }

    @Override
    public Stream<FahrzeugMeta> getAllFahzeuge() {
        try {
            return readJSON().getFahrzeuge().stream().map(FahrzeugMeta::new);
        } catch(IOException e){
            return null;
        }
    }

    @Override
    public Besitzer getBesitzerDetails(int beistzerId) {
        try {
            return readJSON().getBesitzer()
                    .stream()
                    .filter(besitzer -> besitzer.getBesitzerId() == beistzerId)
                    .findFirst()
                    .orElse(null);
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public Fahrzeug getFahrzeugDetails(int fahrzeugId) {
        try {
            return readJSON().getFahrzeuge()
                    .stream()
                    .filter(fahrzeug -> fahrzeug.getFahrzeugId() == fahrzeugId)
                    .findFirst()
                    .orElse(null);
        } catch(IOException e){
            return null;
        }
    }

    @Override
    public int saveBesitzer(Besitzer besitzer) {
        JSON json;
        try {
            json = readJSON();
        } catch(IOException e){
            return -1;
        }
        List<Besitzer> tmpBesitzer = json.getBesitzer();
        if (besitzer.getBesitzerId() == -1) {
            tmpBesitzer.stream()
                    .max(Comparator.comparingInt(BesitzerMeta::getBesitzerId))
                    .ifPresentOrElse(maxBesitzer -> besitzer.setBesitzerId(maxBesitzer.getBesitzerId() + 1), () -> besitzer.setBesitzerId(1));
        } else {
            tmpBesitzer = tmpBesitzer.stream().filter(besitzer1 -> besitzer1.getBesitzerId() != besitzer.getBesitzerId()).collect(Collectors.toList());
        }
        tmpBesitzer.add(besitzer);
        json.setBesitzer(tmpBesitzer);
        try {
            writeJSON(json);
        } catch (IOException e){
            return -1;
        }
        return besitzer.getBesitzerId();
    }

    @Override
    public int saveFahrzeug(Fahrzeug fahrzeug) {
        JSON json;
        try {
            json = readJSON();
        } catch (IOException e) {
            return -1;
        }
        List<Fahrzeug> tmpFahrzeuge = json.getFahrzeuge();
        if (fahrzeug.getFahrzeugId() == -1){
            tmpFahrzeuge.stream()
                    .max(Comparator.comparingInt(FahrzeugMeta::getFahrzeugId))
                    .ifPresentOrElse(maxFahrzeug -> fahrzeug.setFahrzeugId(maxFahrzeug.getFahrzeugId() + 1), () -> fahrzeug.setFahrzeugId(1));
        } else {
            tmpFahrzeuge = tmpFahrzeuge.stream().filter(fahrzeug1 -> fahrzeug1.getFahrzeugId() != fahrzeug.getFahrzeugId()).collect(Collectors.toList());
        }
        tmpFahrzeuge.add(fahrzeug);
        json.setFahrzeuge(tmpFahrzeuge);
        try {
            writeJSON(json);
        } catch(IOException e){
            return -1;
        }
        return fahrzeug.getFahrzeugId();
    }

    @Override
    public boolean deleteFahrzeug(int fahrzeugId) {
        JSON json;
        try {
            json = readJSON();
        } catch (IOException e){
            return false;
        }
        List<Fahrzeug> tmpFahrzeuge = json.getFahrzeuge();
        tmpFahrzeuge = tmpFahrzeuge.stream()
                                   .filter(fahrzeug -> fahrzeug.getFahrzeugId() != fahrzeugId)
                                   .collect(Collectors.toList());
        json.setFahrzeuge(tmpFahrzeuge);
        json = deleteFahrzeugIdFromBesitzverhaeltnisse(json, fahrzeugId);
        try {
            writeJSON(json);
        } catch (IOException e){
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteBesitzer(int besitzerId) {
        JSON json;
        try {
            json = readJSON();
        } catch (IOException e) {
            return false;
        }
        List<Besitzer> tmpBesitzer = json.getBesitzer();
        tmpBesitzer = tmpBesitzer.stream()
                                 .filter(besitzer -> besitzer.getBesitzerId() != besitzerId)
                                 .collect(Collectors.toList());
        json.setBesitzer(tmpBesitzer);
        List<Besitzverhaeltnisse> tmpBesitzverhaeltnisse = json.getBesitzverhaeltnisse();
        tmpBesitzverhaeltnisse = tmpBesitzverhaeltnisse.stream()
                .filter(besitzverhaeltnis -> besitzverhaeltnis.getBesitzerId() != besitzerId)
                .collect(Collectors.toList());
        json.setBesitzverhaeltnisse(tmpBesitzverhaeltnisse);
        try {
            writeJSON(json);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    //TODO
    @Override
    public boolean setNewBesitzer(int fahrzeugId, int besitzerId) {
        JSON json = null;
        try {
            json = readJSON();
        } catch (IOException e){
            return false;
        }
        json = deleteFahrzeugIdFromBesitzverhaeltnisse(json, fahrzeugId);
        if (besitzerId != -1) {
            List<Besitzverhaeltnisse> tmpBesitzverhaeltnisse = json.getBesitzverhaeltnisse();
            Besitzverhaeltnisse newBesitzverhaeltnis = tmpBesitzverhaeltnisse.stream()
                    .filter(besitzverhaeltnisse -> besitzverhaeltnisse.getBesitzerId() == besitzerId)
                    .findFirst()
                    .orElse(null);
            if (newBesitzverhaeltnis != null){
                Set<Integer> tmpFahrzeuge = newBesitzverhaeltnis.getFahrzeuge();
                tmpFahrzeuge.add(fahrzeugId);
                newBesitzverhaeltnis.setFahrzeuge(tmpFahrzeuge);
                List<Besitzverhaeltnisse> allOther = tmpBesitzverhaeltnisse.stream()
                        .filter(besitzverhaeltnisse -> besitzverhaeltnisse.getBesitzerId() != besitzerId)
                        .collect(Collectors.toList());
                allOther.add(newBesitzverhaeltnis);
                json.setBesitzverhaeltnisse(allOther);
                try {
                    writeJSON(json);
                } catch (IOException e){
                    return false;
                }
                return true;
            } else {
                Besitzverhaeltnisse addBesitzverhaeltnis = new Besitzverhaeltnisse();
                addBesitzverhaeltnis.setBesitzerId(besitzerId);
                Set<Integer> fahrzeuge = new HashSet<>();
                fahrzeuge.add(fahrzeugId);
                addBesitzverhaeltnis.setFahrzeuge(fahrzeuge);
                tmpBesitzverhaeltnisse.add(addBesitzverhaeltnis);
                try {
                    writeJSON(json);
                } catch (IOException e){
                    return false;
                }
                return true;
            }
        } else if (besitzerId == -1) {
            try {
                writeJSON(json);
            } catch (IOException e){
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public Stream<FahrzeugMeta> getFahrzeugeByBesitzer(int besitzerId) {
        JSON json = null;
        try {
            json = readJSON();
        } catch (IOException e){
            return null;
        }
        Besitzverhaeltnisse tmpBesitzverhaeltnis = json.getBesitzverhaeltnisse().stream()
                .filter(besitzverhaeltnisse -> besitzverhaeltnisse.getBesitzerId() == besitzerId)
                .findFirst()
                .orElse(null);
        if (tmpBesitzverhaeltnis != null) {
            Set<Integer> tmpFahrzeuge = tmpBesitzverhaeltnis.getFahrzeuge();
            return json.getFahrzeuge().stream()
                    .filter(fahrzeug -> tmpFahrzeuge.contains(fahrzeug.getFahrzeugId()))
                    .map(FahrzeugMeta::new);
        } else return Stream.empty();
    }

    @Override
    public Besitzer getBesitzerByFahrzeug(int fahrzeugId) {
        JSON json = null;
        try {
            json = readJSON();
        } catch (IOException e){
            return null;
        }
        List<Besitzverhaeltnisse> besitzverhaeltnisse = json.getBesitzverhaeltnisse();
        Besitzverhaeltnisse tmpBesitzverhaeltnis  = besitzverhaeltnisse.stream()
                .filter(besitzverhaeltnis -> {
                    if (besitzverhaeltnis.getFahrzeuge() != null) {
                        return besitzverhaeltnis.getFahrzeuge().contains(fahrzeugId);
                    } else return false;
                })
                .findFirst()
                .orElse(null);
        if (tmpBesitzverhaeltnis != null) {
            int tmpBesitzerId = tmpBesitzverhaeltnis.getBesitzerId();
            return json.getBesitzer().stream()
                                     .filter(besitzer -> besitzer.getBesitzerId() == tmpBesitzerId)
                                     .findFirst().orElse(null);
        } else return null;
    }

    private JSON deleteFahrzeugIdFromBesitzverhaeltnisse(JSON json, int fahrzeugId){
        List<Besitzverhaeltnisse> tmpBesitzverhaeltnisse = json.getBesitzverhaeltnisse();
        Besitzverhaeltnisse toDelete = tmpBesitzverhaeltnisse.stream()
                .filter(besitzverhaeltnisse -> {
                    if (besitzverhaeltnisse.getFahrzeuge() != null) {
                        return besitzverhaeltnisse.getFahrzeuge().contains(fahrzeugId);
                    } else return false;})
                .findFirst()
                .orElse(null);
        tmpBesitzverhaeltnisse = tmpBesitzverhaeltnisse.stream()
                .filter(besitzverhaeltnisse -> besitzverhaeltnisse != toDelete)
                .collect(Collectors.toList());
        if (toDelete != null) {
            Set<Integer> tmpFahrzeuge = toDelete.getFahrzeuge();
            tmpFahrzeuge.remove(fahrzeugId);
            toDelete.setFahrzeuge(tmpFahrzeuge);
            tmpBesitzverhaeltnisse.add(toDelete);
        }
        json.setBesitzverhaeltnisse(tmpBesitzverhaeltnisse);
        return json;
        }
}
