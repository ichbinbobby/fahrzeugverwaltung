package components.tui;

import concepts.IFachkonzept;
import models.Fahrzeug;
import utils.Console;

import java.awt.*;
import java.util.Scanner;

public class EditFahrzeug extends AbstractMenu {

    EditFahrzeug(IFachkonzept fachkonzept){
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
        System.out.println("Fahrzeug bearbeiten\n");
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
        System.out.println("Fahrzeug auswählen\n(0 = Zurück)");
//        while (!input.hasNextInt()){
//            System.out.println("Keine gültige Eingabe");
//            choice = input.nextInt();
//        }
        choice = input.nextInt();

        if (choice != 0){
            Fahrzeug fahrzeug = this.fachkonzept.getFahrzeugDetails(choice);
            System.out.println("Neue Bezeichnung des Fahrzeugs "+ fahrzeug.getBezeichnung() +":\n(Leer/0 = Zurück)\n");
            String bezeichnung = input.nextLine().trim();

            if (bezeichnung.isBlank() || bezeichnung.equals("0")) {
                return false;
            } else {
                fahrzeug.setBezeichnung(bezeichnung);
                this.fachkonzept.saveFahrzeug(fahrzeug);
                return false;
            }
        } else return choice != 0;
    }
}
