import concepts.FachkonzeptMock;
import concepts.IFachkonzept;
import models.BesitzerMeta;
import models.FahrzeugMeta;

import java.util.stream.Stream;

public class Fahrzeugverwaltung {
    public static void main(String[] args) {
        IFachkonzept fachkonzept = new FachkonzeptMock();

        System.out.println("All Besitzer");
        fachkonzept.getAllBesitzer().map(BesitzerMeta::getName).forEach(System.out::println);
        System.out.println();

        System.out.println("All Fahrzeuge");
        fachkonzept.getAllFahrzeuge().map(FahrzeugMeta::getBezeichnung).forEach(System.out::println);
        System.out.println();

        System.out.println("Besitzer by Fahrzeug 1");
        System.out.println(fachkonzept.getBesitzerByFahrzeug(1));
        System.out.println();

        System.out.println("Besitzer by Fahrzeug 15");
        System.out.println(fachkonzept.getBesitzerByFahrzeug(15));
        System.out.println();

        System.out.println("Fahrzeuge by Besitzer 1");
        Stream<FahrzeugMeta> fahrzeugeByBesitzer = fachkonzept.getFahrzeugeByBesitzer(1);
        fahrzeugeByBesitzer.map(FahrzeugMeta::getBezeichnung).forEach(System.out::println);
        System.out.println(fahrzeugeByBesitzer);
        System.out.println();


        System.out.println("Fahrzeuge by Besitzer 15");
        System.out.println(fahrzeugeByBesitzer);
        System.out.println();

        System.out.println("Besitzer Details by 1");
        System.out.println(fachkonzept.getBesitzerDetails(1));
        System.out.println();

        System.out.println("Besitzer Details by 15");
        System.out.println(fachkonzept.getBesitzerDetails(15));
        System.out.println();

        System.out.println("Fahrzeug Details by 1");
        System.out.println(fachkonzept.getFahrzeugDetails(1));
        System.out.println();

        System.out.println("Fahrzeug Details by 15");
        System.out.println(fachkonzept.getFahrzeugDetails(15));
        System.out.println();

        System.out.printf("Delete Besitzer 1: %b", fachkonzept.deleteBesitzer(1));
        System.out.println();
        System.out.printf("Delete Besitzer 15: %b", fachkonzept.deleteBesitzer(15));
        System.out.println();
        System.out.println();

        System.out.println("All Besitzer");
        fachkonzept.getAllBesitzer().map(BesitzerMeta::getName).forEach(System.out::println);
        System.out.println();

        System.out.println("Fahrzeuge by Besitzer 1");
        fachkonzept.getFahrzeugeByBesitzer(1).map(FahrzeugMeta::getBezeichnung).forEach(System.out::println);

    }
}
