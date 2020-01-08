package components.tui;

import concepts.IFachkonzept;
import models.Fahrzeug;
import utils.Console;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EditFahrzeug extends MainMenu {
    private List<Integer> ids = new ArrayList<Integer>();

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
        var ref = new Object() {
            int counter = 1;
        };
        this.fachkonzept.getAllFahrzeuge().forEach(currFahrzeug -> {
            System.out.println(ref.counter + ":  " + currFahrzeug.getBezeichnung());
            this.ids.add(currFahrzeug.getFahrzeugId());
            ref.counter = ref.counter + 1;
        });
        System.out.println(seperatorLine);
    }

    @Override
    public boolean getUserChoice() {
        System.out.println("Fahrzeug auswählen\n(0 = Zurück)");
        int choice = Console.inputInt();

        if (choice != 0){
            Fahrzeug fahrzeug = this.fachkonzept.getFahrzeugDetails(this.ids.get(choice - 1));
            System.out.println("Neue Bezeichnung des Fahrzeugs "+ fahrzeug.getBezeichnung() +":\n(Leer/0 = Zurück)");
            String bezeichnung = Console.inputString();

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
