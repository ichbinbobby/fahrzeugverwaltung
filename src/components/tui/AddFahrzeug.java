package components.tui;

import concepts.IFachkonzept;
import models.Fahrzeug;
import utils.Console;

import java.util.Scanner;

public class AddFahrzeug extends MainMenu {

    AddFahrzeug(IFachkonzept fachkonzept){
        super(fachkonzept);
    }

    @Override
    public void showMenuInfo() {
        Console.clear();
        System.out.println("Fahrzeug hinzufügen");
    }

    @Override
    public void showReachableMenus() {
        System.out.println("-".repeat(25));
    }

    @Override
    public boolean getUserChoice() {
        System.out.println("Name des neuen Fahrzeugs:\n(Leer/0 = Zurück)");

        String name = Console.inputString();

        if (name.isBlank() || name.equals("0")) {
            return false;
        } else {
            int result = this.fachkonzept.saveFahrzeug(new Fahrzeug(-1, name));
            if (result > 0){
                System.out.println("Neues Fahrzeug '" + name + "' unter der ID '" + result + "' erstellt.");
                Console.pressEnterToContinue();
                return false;
            } else {
                System.out.println("Es ist ein Fehler aufgetreten.");
                Console.pressEnterToContinue();
                return true;
            }
        }
    }
}
