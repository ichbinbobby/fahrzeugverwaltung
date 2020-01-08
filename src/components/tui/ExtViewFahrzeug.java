package components.tui;

import concepts.IFachkonzept;
import models.Besitzer;
import utils.Console;

import java.util.Scanner;

public class ExtViewFahrzeug extends MainMenu {
    private int fahrzeugId;

    ExtViewFahrzeug(IFachkonzept fachkonzept, int fahrzeugId){
        super(fachkonzept);
        this.fahrzeugId = fahrzeugId;
    }

    @Override
    public void showMenuInfo() {
        Console.clear();
        System.out.println("Fahrzeug Informationen");
    }

    @Override
    public void showReachableMenus() {
        int fahrzeugId = this.fahrzeugId;
        String bezeichnung = this.fachkonzept.getFahrzeugDetails(fahrzeugId).getBezeichnung();
        Besitzer besitzer = this.fachkonzept.getBesitzerByFahrzeug(fahrzeugId);
        String separatorLine = "-".repeat(22);
        String besitzerName = "Kein Besitzer";
        if (besitzer != null){
            besitzerName = besitzer.getName();
        }

        System.out.println(separatorLine);
        System.out.println("Fahrzeug: " + bezeichnung);
        System.out.println("Besitzer: " + besitzerName);
        System.out.println(separatorLine);
    }

    @Override
    public boolean getUserChoice() {
        Scanner input = new Scanner(System.in);

        System.out.print("(Leer/0 = Zurück)\n> ");
        while (true){
            String choice = input.nextLine().trim();
            if (choice.isBlank() || choice.equals("0")) {
                return false;
            }
        }
    }
}
