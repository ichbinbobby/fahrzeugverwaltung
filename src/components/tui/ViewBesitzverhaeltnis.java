package components.tui;

import concepts.IFachkonzept;
import models.FahrzeugMeta;
import utils.Console;

import java.util.Scanner;
import java.util.stream.Stream;

public class ViewBesitzverhaeltnis extends MainMenu {

    ViewBesitzverhaeltnis(IFachkonzept fachkonzept){
        super(fachkonzept);
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
        this.fachkonzept.getAllBesitzer().forEach(currbesitzer -> {
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
        System.out.println("(Leer/0 = Zurück)");
        while (true){
            String choice = Console.inputString();
            if (choice.isBlank() || choice.equals("0")) {
                return false;
            }
        }
    }
}
