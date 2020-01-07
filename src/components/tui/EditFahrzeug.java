package components.tui;

import concepts.IFachkonzept;
import models.Fahrzeug;
import utils.Console;

import java.util.Scanner;

public class EditFahrzeug extends MainMenu {

    EditFahrzeug(IFachkonzept fachkonzept){
        super(fachkonzept);
    }

    @Override
    public void showMenuInfo() {
        Console.clear();
        System.out.println("Fahrzeug bearbeiten");
    }

    @Override
    public void showReachableMenus() {
        String seperatorLine = "-".repeat(19);
        System.out.println(seperatorLine);
        this.fachkonzept.getAllFahrzeuge().forEach(currFahrzeug -> {
            System.out.println(currFahrzeug.getFahrzeugId() + ":  " + currFahrzeug.getBezeichnung());
        });
        System.out.println(seperatorLine);
    }

    @Override
    public boolean getUserChoice() {
        Scanner input = new Scanner(System.in);
        int choice = -1;
        System.out.print("Fahrzeug auswählen\n(0 = Zurück)\n> ");
//        while (!input.hasNextInt()){
//            System.out.println("Keine gültige Eingabe");
//            choice = input.nextInt();
//        }
        choice = input.nextInt();

        if (choice != 0){
            Fahrzeug fahrzeug = this.fachkonzept.getFahrzeugDetails(choice);
            System.out.print("Neue Bezeichnung des Fahrzeugs "+ fahrzeug.getBezeichnung() +":\n(Leer/0 = Zurück)\n> ");
            String bezeichnung = input.next().trim();

            if (bezeichnung.isBlank() || bezeichnung.equals("0")) {
                return false;
            } else {
                fahrzeug.setBezeichnung(bezeichnung);
                if (this.fachkonzept.saveFahrzeug(fahrzeug) >= 0){
                    System.out.println("Neue Bezeichnung '" + bezeichnung + "' wurde gespeichert.");
                    Console.pressEnterToContinue();
                    return false;
                } else {
                    System.out.println("Es ist ein Fehler aufgetreten.");
                    Console.pressEnterToContinue();
                    return false;
                }
            }
        } else return choice != 0;
    }
}
