package components.tui;

import concepts.IFachkonzept;
import models.Fahrzeug;
import utils.Console;

import java.util.Scanner;

public class AddFahrzeug extends AbstractMenu {
    private IFachkonzept fachkonzept;

    AddFahrzeug(IFachkonzept fachkonzept){
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
        System.out.println("Fahrzeug hinzufügen");
    }

    @Override
    public void showReachableMenus() {
        System.out.println("-".repeat(25));
    }

    @Override
    public boolean getUserChoice() {
        Scanner input = new Scanner(System.in);
        System.out.print("Name des neuen Fahrzeugs:\n(Leer/0 = Zurück)\n> ");

        String name = input.nextLine().trim();

        if (name.isBlank() || name.equals("0")) {
            return false;
        } else {
            int result = this.fachkonzept.saveFahrzeug(new Fahrzeug(-1, name));
            if (result > 0){
                System.out.println("Neues Fahrzeug '" + name + "' unter der ID '" + result + "' erstellt.");
                System.out.println("Drücke ENTER um zum Hauptmenu zurück zu kommen.");
                input.nextLine();
                return false;
            } else {
                System.out.println("Es ist ein Fehler aufgetreten. ");
                return true;
            }
        }
    }
}
