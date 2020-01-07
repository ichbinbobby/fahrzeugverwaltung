package components.tui;

import concepts.IFachkonzept;
import models.Besitzer;
import models.BesitzerMeta;
import models.FahrzeugMeta;
import utils.Console;

import java.util.Scanner;
import java.util.stream.Stream;

public class ViewBesitzverhaeltnis extends AbstractMenu {
    Stream<BesitzerMeta> besitzer;

    ViewBesitzverhaeltnis(IFachkonzept fachkonzept){
        this.fachkonzept = fachkonzept;
        boolean run = true;

        while (run){
            showMenuInfo();
            showReachableMenus();
            run = getUserChoice();
        }
    }

    @Override
    public void showMenuInfo() {
        Console.clear();
        System.out.println("Besitzverhältnisse anzeigen");
    }

    @Override
    public void showReachableMenus() {
        String separatorLine = "-".repeat(21);
        System.out.println(separatorLine);
        Stream<BesitzerMeta> besitzer = this.fachkonzept.getAllBesitzer();
        besitzer.forEach(currbesitzer -> {
            System.out.println("Name: " + currbesitzer.getName());
            System.out.println("Fahrzeug/e: ");
            Stream<FahrzeugMeta> fahrzeuge = this.fachkonzept.getFahrzeugeByBesitzer(currbesitzer.getBesitzerId());
            if (fahrzeuge != null){
                fahrzeuge.forEach(fahrzeug ->{
                    System.out.println("\t" + fahrzeug.getBezeichnung());
                });
            } else {
                System.out.println("Keine Fahreuge");
            }
            });
        System.out.println(separatorLine);
    }

    @Override
    public boolean getUserChoice() {
        Scanner input = new Scanner(System.in);

        System.out.println("(Leer/0 = Zurück)");
        while (true){
            String choice = input.nextLine().trim();
            if (choice.isBlank() || choice.equals("0")) {
                return false;
            }
        }
    }
}
