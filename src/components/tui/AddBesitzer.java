package components.tui;

import concepts.IFachkonzept;
import models.Besitzer;
import utils.Console;

import java.util.Scanner;

public class AddBesitzer extends MainMenu {

    AddBesitzer(IFachkonzept fachkonzept){
        super(fachkonzept);
    }


    @Override
    public void showMenuInfo() {
        Console.clear();
        System.out.println("Besitzer hinzufügen");
    }

    @Override
    public void showReachableMenus() {
        System.out.println("-".repeat(25));
    }

    @Override
    public boolean getUserChoice() {
        Scanner input = new Scanner(System.in);
        System.out.print("Name des neuen Besitzers:\n(Leer/0 = Zurück)\n> ");

        String name = input.nextLine().trim();

        if (name.isBlank() || name.equals("0")) {
            return false;
        } else {
            int result = this.fachkonzept.saveBesitzer(new Besitzer(-1, name));
            if (result >= 0){
                System.out.println("Neuer Besitzer '" + name + "' unter der ID '" + result + "' erstellt.");
                Console.pressEnterToContinue();
                return false;
            } else {
                System.out.println("Es ist ein Fehler aufgetreten." + result);
                Console.pressEnterToContinue();
                return true;
            }
        }
    }
}
