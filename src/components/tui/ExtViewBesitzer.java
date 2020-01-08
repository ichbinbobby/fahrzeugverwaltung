package components.tui;

import concepts.IFachkonzept;
import utils.Console;

import java.util.Scanner;

public class ExtViewBesitzer extends MainMenu {
    private int besitzerId;

    ExtViewBesitzer(IFachkonzept fachkonzept, int besitzerId){
        super(fachkonzept);
        this.besitzerId = besitzerId;
    }

    @Override
    public void showMenuInfo() {
        Console.clear();
        System.out.println("Besitzer Informationen");
    }

    @Override
    public void showReachableMenus() {
        String separatorLine = "-".repeat(22);
        String name = this.fachkonzept.getBesitzerDetails(besitzerId).getName();

        System.out.println(separatorLine);
        System.out.println("Name: "+ name);
        System.out.println("Fahrzeuge:");
        this.fachkonzept.getFahrzeugeByBesitzer(besitzerId).forEach(fahrzeug ->{
            System.out.println("\t" + fahrzeug.getBezeichnung());
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
