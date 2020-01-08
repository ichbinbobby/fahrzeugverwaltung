package components.tui;

import concepts.IFachkonzept;
import utils.Console;

import java.util.Scanner;

public class DeleteFahrzeug extends MainMenu {

    DeleteFahrzeug(IFachkonzept fachkonzept){
        super(fachkonzept);
    }

    @Override
    public void showMenuInfo() {
        Console.clear();
        System.out.println("Fahrzeug löschen");
    }

    @Override
    public void showReachableMenus() {
        String separatorLine = "-".repeat(16);
        System.out.println(separatorLine);
        this.fachkonzept.getAllFahrzeuge().forEach(currFahrzeug -> {
            System.out.println(currFahrzeug.getFahrzeugId() + ":  " + currFahrzeug.getBezeichnung());
        });
        System.out.println(separatorLine);
    }

    @Override
    public boolean getUserChoice() {
        System.out.println("Fahrzeug auswählen\n(0 = Zurück)");
        int choice = Console.inputInt();

        if (choice != 0){
            if (this.fachkonzept.deleteFahrzeug(choice)) {
                System.out.println("Löschen erfolgreich.");
                Console.pressEnterToContinue();
                return false;
            } else {
                System.out.println("Es gab einen Fehler.");
                Console.pressEnterToContinue();
                return true;
            }
        } else return choice != 0;
    }
}
